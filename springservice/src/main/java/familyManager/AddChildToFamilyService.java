package familyManager;

import familyManager.api.Child;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class AddChildToFamilyService {
    private final FamilyRepository repository;

    @Autowired
    AddChildToFamilyService(FamilyRepository repository) {
        this.repository = repository;
    }

    void addChildToFamily(long familyId, Child child) {
        repository.addChildToFamily(familyId, child);
    }
}
