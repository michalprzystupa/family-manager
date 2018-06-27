package familyManager;

import familyManager.api.Father;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ReadFatherService {
    private final FamilyRepository repository;

    @Autowired
    ReadFatherService(FamilyRepository repository) {
        this.repository = repository;
    }

    Father readFather(long familyId) {
        return repository.readFather(familyId);
    }
}
