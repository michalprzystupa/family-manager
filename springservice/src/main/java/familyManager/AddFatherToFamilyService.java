package familyManager;

import familyManager.api.Father;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class AddFatherToFamilyService {
    private final FamilyRepository repository;

    @Autowired
    AddFatherToFamilyService(FamilyRepository repository) {
        this.repository = repository;
    }

    void addFatherToFamily(long familyId, Father father) {
        repository.addFatherToFamily(familyId, father);
    }
}
