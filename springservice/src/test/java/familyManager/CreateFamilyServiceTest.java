package familyManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CreateFamilyServiceTest {
    private final String SELECT_COUNT_FROM_FAMILY_QUERY = "SELECT count(*) FROM FAMILY";

    @Autowired
    private CreateFamilyService subject;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void shouldCreateNewFamily() throws Exception {
        assertEquals(0, getFamilyCount());

        subject.createFamily();

        assertEquals(1, getFamilyCount());
    }

    private long getFamilyCount() {
        return jdbcTemplate.queryForObject(SELECT_COUNT_FROM_FAMILY_QUERY, Long.class);
    }

}