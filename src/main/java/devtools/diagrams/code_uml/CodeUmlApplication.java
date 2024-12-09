package devtools.diagrams.code_uml;

import devtools.diagrams.code_uml.json.tool.TypeDocInformationJSONReader;
import devtools.diagrams.code_uml.redis.repository.TypeInformationRedisRepository;
import devtools.diagrams.code_uml.redis.tool.TypeInformationFilesToRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class CodeUmlApplication implements CommandLineRunner {

	@Autowired
	TypeInformationFilesToRedis toolTypeInformationFilesToRedis;

	@Autowired
	TypeDocInformationJSONReader toolSuperTypeInformationToRedis;
	
	@Autowired
	public CodeUmlApplication(TypeInformationFilesToRedis toolTypeInformationFilesToRedis, 
			TypeDocInformationJSONReader toolSuperTypeInformationToRedis) {
		this.toolTypeInformationFilesToRedis =  toolTypeInformationFilesToRedis;
		this.toolSuperTypeInformationToRedis = toolSuperTypeInformationToRedis;
	}

	public static void main(String[] args) {
		SpringApplication.run(CodeUmlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Running the CommandLiner -------------------------------------------------");
		// toolTypeInformationFilesToRedis.sendFilesToRedis();
		toolSuperTypeInformationToRedis.sendSuperTypeInformationToRedis();
	}
}
