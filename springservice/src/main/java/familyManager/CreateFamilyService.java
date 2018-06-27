package familyManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class CreateFamilyService {
    private final Repository repository;

    @Autowired
    CreateFamilyService(Repository repository) {
        this.repository = repository;
    }

    long createFamily() {
        return repository.createFamily();
    }
}