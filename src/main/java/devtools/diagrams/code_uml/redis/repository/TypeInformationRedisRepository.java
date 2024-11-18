package devtools.diagrams.code_uml.redis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TypeInformationRedisRepository {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    public void save(String fullyQualifiedTypeName, String jsonObject) {
        redisTemplate.opsForValue().set(fullyQualifiedTypeName,jsonObject);
    }
}
