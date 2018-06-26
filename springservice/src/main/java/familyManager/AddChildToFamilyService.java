package familyManager;

import familyManager.api.dto.Child;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class AddChildToFamilyService {
    private final Repository repository;

    @Autowired
    AddChildToFamilyService(Repository repository) {
        this.repository = repository;
    }

    void addChildToFamily(long familyId, Child child) {
        repository.addChildToFamily(familyId, child);
    }
}
