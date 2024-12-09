package devtools.diagrams.code_uml.json.tool;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import devtools.diagrams.code_uml.entity.type.TypeInformation;
import devtools.diagrams.code_uml.entity.type.Types;
import devtools.diagrams.code_uml.redis.repository.TypeInformationRedisRepository;

@Component
public class TypeDocInformationJSONReader {

    private static final Logger logger = LoggerFactory.getLogger(TypeDocInformationJSONReader.class);

    @Autowired
    TypeInformationRedisRepository typeInformationRedisRepository;
    private final String sourceFilesPath = "C:\\apidocs-json\\docs";


    public void sendSuperTypeInformationToRedis() {
        File files = new File(sourceFilesPath);
        if ( files.isDirectory() ) {
            File[] typeInformationFiles =  files.listFiles();
            HashMap<String, Integer> typeKeyIndexMap = new HashMap<>();
            
            Arrays.stream(typeInformationFiles).forEach(
                    typeInformationFile -> {
                        try {
                            FileReader jsonContentReader = new FileReader(typeInformationFile.getAbsolutePath());
                            String typeInformationFullyQualifiedFileName = typeInformationFile.getName();
                            
                            JsonElement jsonElement = JsonParser.parseReader(jsonContentReader).getAsJsonObject();
                            JsonObject jsonObject = jsonElement.getAsJsonObject();
                            JsonArray interfaces = jsonObject.getAsJsonArray("interfaces");
                            String typeQualifiedName = jsonObject.get("qualifiedName").getAsString();
							Integer keyIndex = typeKeyIndexMap.get(typeQualifiedName);
							
							if ( keyIndex == null ) {
								keyIndex = 0;
								typeKeyIndexMap.put(typeQualifiedName, keyIndex);
							}
							
							TypeInformation typeInformation = new TypeInformation(typeQualifiedName, Types.CLASS);
                            if ( interfaces != null ) {
                            	interfaces.forEach(interfaceObj -> {
                            		String interfaceFullyQualifiedName = interfaceObj.getAsJsonObject().get("qualifiedName").getAsString();
                            		String interfaceType = interfaceObj.getAsJsonObject().get("type").getAsString();
                            		Integer sKeyIndex = typeKeyIndexMap.get(typeQualifiedName);                            		
                            		TypeInformation superInterfaceTypeInformation = new TypeInformation(interfaceType, Types.INTERFACE);
                            		typeInformationRedisRepository.setSuperTypesInformationForType(sKeyIndex++,typeInformation, superInterfaceTypeInformation);
                            		typeKeyIndexMap.put(typeQualifiedName, sKeyIndex);

                            	});
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

        } else {
            logger.warn("Source Path is not a directory or empty path");
        }
    }
}
