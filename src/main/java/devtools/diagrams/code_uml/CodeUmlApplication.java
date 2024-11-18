package devtools.diagrams.code_uml;

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
	public CodeUmlApplication(TypeInformationFilesToRedis toolTypeInformationFilesToRedis) {
		this.toolTypeInformationFilesToRedis =  toolTypeInformationFilesToRedis;
	}

	public static void main(String[] args) {
		SpringApplication.run(CodeUmlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Running the CommandLiner -------------------------------------------------");
		toolTypeInformationFilesToRedis.sendFilesToRedis();
	}
}
