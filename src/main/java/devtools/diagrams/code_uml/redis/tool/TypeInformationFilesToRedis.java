package devtools.diagrams.code_uml.redis.tool;

import devtools.diagrams.code_uml.redis.repository.TypeInformationRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

@Component
public class TypeInformationFilesToRedis {

    private static final Logger logger = LoggerFactory.getLogger(TypeInformationFilesToRedis.class);
    @Autowired
    TypeInformationRedisRepository redisRepository;

    private final String sourcFilesPath = "C:\\apidocs-json\\docs";

    public void sendFilesToRedis() {
        File file = new File(sourcFilesPath);
        if ( file.isDirectory()) {
            File[] typeInformationFiles =  file.listFiles();
            Arrays.stream(typeInformationFiles).forEach(
                    typeInformationFile -> {
                        try {
                            String jsonContent = new String(Files.readAllBytes(typeInformationFile.toPath()));
                            String typeInformationFullyQualifiedFileName = typeInformationFile.getName();
                            redisRepository.save(typeInformationFullyQualifiedFileName,jsonContent);
                        } catch (IOException  e) {
                            throw new RuntimeException(e);
                        }
                    });
        } else {
            logger.warn("Source Path is not a directory or empty path");
        }
    }
}
