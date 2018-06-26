package familyManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
class CreateFamilyService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    CreateFamilyService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    long createFamily() {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("Family").usingGeneratedKeyColumns("id");
        return jdbcInsert.executeAndReturnKey(new HashMap<>()).longValue();
    }
}