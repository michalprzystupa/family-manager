package familyManager;

import familyManager.api.Child;
import familyManager.api.Family;
import familyManager.api.Father;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/families")
class FamilyController {
    private final CreateFamilyService createFamilyService;
    private final AddFatherToFamilyService addFatherToFamilyService;
    private final AddChildToFamilyService addChildToFamilyService;
    private final ReadFamilyService readFamilyService;
    private final SearchByChildService searchChildService;
    private final ReadChildService readChildService;
    private final ReadFatherService readFatherService;

    @Autowired
    FamilyController(CreateFamilyService createFamilyService,
                     AddFatherToFamilyService addFatherToFamilyService,
                     AddChildToFamilyService addChildToFamilyService,
                     ReadFamilyService readFamilyService,
                     SearchByChildService searchChildService,
                     ReadChildService readChildService,
                     ReadFatherService readFatherService) {
        this.createFamilyService = createFamilyService;
        this.addFatherToFamilyService = addFatherToFamilyService;
        this.addChildToFamilyService = addChildToFamilyService;
        this.readFamilyService = readFamilyService;
        this.searchChildService = searchChildService;
        this.readChildService = readChildService;
        this.readFatherService = readFatherService;
    }

    @GetMapping
    List<Family> searchByChild(@RequestParam(defaultValue = "", required = false) String firstName,
                               @RequestParam(defaultValue = "", required = false) String secondName,
                               @RequestParam(defaultValue = "", required = false) String pesel,
                               @RequestParam(defaultValue = "", required = false) String sex) {
        return searchChildService.searchFamiliesByChildParameters(firstName, secondName, pesel, sex);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    long createFamily() {
        return createFamilyService.createFamily();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{familyId}/father")
    void addFatherToFamily(@PathVariable int familyId, @RequestBody Father father) {
        addFatherToFamilyService.addFatherToFamily(familyId, father);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{familyId}/children")
    void addChildToFamily(@PathVariable int familyId, @RequestBody Child child) {
        addChildToFamilyService.addChildToFamily(familyId, child);
    }

    @GetMapping("/{familyId}")
    Family readFamily(@PathVariable long familyId) {
        return readFamilyService.readFamily(familyId);
    }

    @GetMapping("/{familyId}/children")
    List<Child> readChild(@PathVariable int familyId) {
        return readChildService.readChildren(familyId);
    }

    @GetMapping("/{familyId}/father")
    Father readFather(@PathVariable int familyId) {
        return readFatherService.readFather(familyId);
    }
}
