package familyManager;

import familyManager.api.dto.Child;
import familyManager.api.dto.Family;
import familyManager.api.dto.Father;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ReadFamilyService {
    private final ReadFatherService readFatherService;
    private final ReadChildService readChildService;
    private final SearchChildService searchChildService;

    @Autowired
    public ReadFamilyService(ReadFatherService readFatherService, ReadChildService readChildService, SearchChildService searchChildService) {
        this.readFatherService = readFatherService;
        this.readChildService = readChildService;
        this.searchChildService = searchChildService;
    }


    Family readFamily(long familyId) {
        Father father = readFatherService.readFather(familyId);
        List<Child> children = readChildService.readChildren(familyId);

        return new Family(father, children);
    }
}
