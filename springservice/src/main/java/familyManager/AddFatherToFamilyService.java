package familyManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
class AddFatherToFamilyService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    AddFatherToFamilyService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    void addFatherToFamily(long familyId, Father father) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("Father").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("firstName", father.getFirstName());
        parameters.put("secondName", father.getSecondName());
        parameters.put("pesel", father.getPesel());
        parameters.put("birthDate", father.getBirthDate());
        parameters.put("familyId", familyId);
        jdbcInsert.execute(parameters);
    }
}
