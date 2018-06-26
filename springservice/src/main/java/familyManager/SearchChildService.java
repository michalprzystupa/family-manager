package familyManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class SearchChildService {
    private final Repository repository;

    @Autowired
    SearchChildService(Repository repository) {
        this.repository = repository;
    }

    List<Long> searchByChild(String firstName, String secondName, String pesel, String sex) {
        return repository.searchFamilyByChild(firstName, secondName, pesel, sex);
    }
}
