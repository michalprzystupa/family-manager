package familyManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
class ReadFatherService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    ReadFatherService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    Father readFather(long familyId) {
        return jdbcTemplate.queryForObject("Select * FROM Father WHERE familyId = ?", new Object[]{familyId},
                (resultSet, i) -> new Father(
                        resultSet.getString("pesel"),
                        resultSet.getString("firstName"),
                        resultSet.getString("secondName"),
                        resultSet.getDate("birthDate").toLocalDate()));
    }
}
