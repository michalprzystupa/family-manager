package familyManager;

import familyManager.api.Child;
import familyManager.api.Father;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Statement;
import java.util.List;

@Repository
class FamilyRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    FamilyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    long createFamily() {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> connection.prepareStatement(Query.CREATE_FAMILY, Statement.RETURN_GENERATED_KEYS), holder);
        return holder.getKey().longValue();
    }

    void addFatherToFamily(long familyId, Father father) {
        jdbcTemplate.update(Query.CREATE_FATHER,
                father.getFirstName(), father.getSecondName(), father.getPesel(), father.getBirthDate(), familyId);
    }

    void addChildToFamily(long familyId, Child child) {
        jdbcTemplate.update(Query.CREATE_CHILD,
                child.getFirstName(), child.getSecondName(), child.getPesel(), child.getSex(), familyId);
    }

    Father readFather(long familyId) {
        return jdbcTemplate.queryForObject(Query.SELECT_FATHER_BY_FAMILY_ID, new Object[]{familyId},
                (resultSet, i) -> new Father(
                        resultSet.getString("pesel"),
                        resultSet.getString("firstName"),
                        resultSet.getString("secondName"),
                        resultSet.getDate("birthDate").toLocalDate()));
    }

    List<Child> readChild(long familyId) {
        return jdbcTemplate.query(Query.SELECT_CHILD_BY_FAMILY_ID, new Object[]{familyId},
                (resultSet, i) -> new Child(
                        resultSet.getString("pesel"),
                        resultSet.getString("firstName"),
                        resultSet.getString("secondName"),
                        resultSet.getString("sex")));
    }

    List<Long> searchFamilyIdsByChildParameters(String firstName, String secondName, String pesel, String sex) {
        return jdbcTemplate.queryForList(Query.SELECT_DISTINCT_FAMILY_ID_BY_CHILD_PARAMETERS,
                new Object[]{"%" + firstName + "%", "%" + secondName + "%", "%" + pesel + "%", "%" + sex + "%"},
                Long.class);
    }
}
