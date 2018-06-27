package familyManager;

import familyManager.api.dto.Father;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class AddFatherToFamilyService {
    private final Repository repository;

    @Autowired
    AddFatherToFamilyService(Repository repository) {
        this.repository = repository;
    }

    void addFatherToFamily(long familyId, Father father) {
        repository.addFatherToFamily(familyId, father);
    }
}
