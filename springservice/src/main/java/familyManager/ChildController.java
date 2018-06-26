package familyManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/child")
class ChildController {
    private final SearchChildService searchChildService;

    @Autowired
    ChildController(SearchChildService searchChildService) {
        this.searchChildService = searchChildService;
    }

    @GetMapping
    List<Long> searchByChild(@RequestParam String firstName,
                             @RequestParam String secondName,
                             @RequestParam String pesel,
                             @RequestParam String sex) {
        return searchChildService.searchByChild(firstName, secondName, pesel, sex);
    }
}
