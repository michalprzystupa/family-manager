package familyManager;

import familyManager.api.dto.Child;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ReadChildService {
    private final Repository repository;

    @Autowired
    ReadChildService(Repository repository) {
        this.repository = repository;
    }

    List<Child> readChildren(long familyId) {
        return repository.readChild(familyId);
    }
}
