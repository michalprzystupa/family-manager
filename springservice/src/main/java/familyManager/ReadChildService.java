package familyManager;

import familyManager.api.Child;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ReadChildService {
    private final FamilyRepository repository;

    @Autowired
    ReadChildService(FamilyRepository repository) {
        this.repository = repository;
    }

    List<Child> readChildren(long familyId) {
        return repository.readChild(familyId);
    }
}
