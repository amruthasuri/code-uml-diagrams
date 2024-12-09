package devtools.diagrams.code_uml.redis.tool;

import devtools.diagrams.code_uml.redis.repository.TypeInformationRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TypeHeirarchyEntriesCreator {

        @Autowired
        private TypeInformationRedisRepository typeInformationRedisRepository;

         public void createTypeHeirarchyEntries() {

         }

}
