package devtools.diagrams.code_uml.redis.repository;

import devtools.diagrams.code_uml.entity.type.TypeInformation;
import devtools.diagrams.code_uml.entity.type.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TypeInformationRedisRepository {

    private static final String DELIMITER = ":";
    
	@Autowired
    RedisTemplate<String,String> redisTemplate;

    public void save(String fullyQualifiedTypeName, String jsonObject) {
        redisTemplate.opsForValue().set(fullyQualifiedTypeName,jsonObject);
    }

    public void setSuperTypesInformationForType
            (Integer keyIndex, TypeInformation typeInformation, TypeInformation superTypeInformation) {
        redisTemplate.opsForHash().put("SuperTypes", typeInformation.toString() + DELIMITER + keyIndex,superTypeInformation.toString());
    }
}
