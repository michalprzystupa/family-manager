package familyManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ReadChildService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    ReadChildService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    List<Child> readChildren(long familyId) {
        return jdbcTemplate.query("Select * FROM Child WHERE familyId = ?", new Object[]{familyId}, (resultSet, i) -> new Child(
                resultSet.getString("pesel"),
                resultSet.getString("firstName"),
                resultSet.getString("secondName"),
                resultSet.getString("sex")));
    }
}
