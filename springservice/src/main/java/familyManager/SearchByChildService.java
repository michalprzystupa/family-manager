package familyManager;

import familyManager.api.Family;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class SearchByChildService {
    private final ReadFamilyService readFamilyService;
    private final FamilyRepository repository;

    @Autowired
    SearchByChildService(ReadFamilyService readFamilyService, FamilyRepository repository) {
        this.readFamilyService = readFamilyService;
        this.repository = repository;
    }

    List<Family> searchFamiliesByChildparameters(String firstName, String secondName, String pesel, String sex) {
        List<Long> familyIds = repository.searchFamilyIdsByChildParameters(firstName, secondName, pesel, sex);
        return familyIds.stream()
                .map(readFamilyService::readFamily)
                .collect(Collectors.toList());
    }
}
