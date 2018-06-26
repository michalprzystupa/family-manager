package familyManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Repository
class Repository {
    private final String SEARCH_FAMILY = "SELECT DISTINCT familyId FROM Child WHERE pesel LIKE ?";
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public Repository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    List<Long> searchFamilyByChild(String firstName, String secondName, String pesel, String sex) {
        return jdbcTemplate.queryForList(SEARCH_FAMILY, new Object[]{"%" + pesel + "%"}, Long.class);
    }

    void addChildToFamily(long familyId, Child child) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("Child").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("firstName", child.getFirstName());
        parameters.put("secondName", child.getSecondName());
        parameters.put("pesel", child.getPesel());
        parameters.put("sex", child.getSex());
        parameters.put("familyId", familyId);
        jdbcInsert.execute(parameters);
    }
}
