package familyManager;

import familyManager.api.Child;
import familyManager.api.Family;
import familyManager.api.Father;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ReadFamilyService {
    private final ReadFatherService readFatherService;
    private final ReadChildService readChildService;

    @Autowired
    public ReadFamilyService(ReadFatherService readFatherService, ReadChildService readChildService) {
        this.readFatherService = readFatherService;
        this.readChildService = readChildService;
    }

    Family readFamily(long familyId) {
        Father father = readFatherService.readFather(familyId);
        List<Child> children = readChildService.readChildren(familyId);

        return new Family(father, children);
    }
}
