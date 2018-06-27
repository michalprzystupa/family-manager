package familyManager;

import familyManager.api.dto.Father;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ReadFatherService {
    private final Repository repository;

    @Autowired
    ReadFatherService(Repository repository) {
        this.repository = repository;
    }

    Father readFather(long familyId) {
        return repository.readFather(familyId);
    }
}
