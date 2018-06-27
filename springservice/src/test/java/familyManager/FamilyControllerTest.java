package familyManager;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import familyManager.api.dto.Child;
import familyManager.api.dto.Family;
import familyManager.api.dto.Father;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileReader;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class FamilyControllerTest {
    private static final int FIRST_CREATED_FAMILY_ID = 1;
    private final String FAMILIES_URL = "http://localhost:8081/families";
    private final String SELECT_COUNT_FROM_FAMILY_QUERY = "SELECT count(*) FROM FAMILY";

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private FamilyController subject;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void shouldCreateNewFamily() throws Exception {
        assertEquals(0, getFamilyCount());

        MockHttpServletResponse response = mockMvc.perform(post(FAMILIES_URL))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn()
                .getResponse();
        long familyId = Long.decode(response.getContentAsString());

        assertEquals(1, getFamilyCount());
        assertEquals(FIRST_CREATED_FAMILY_ID, familyId);
    }

    @Test
    public void shouldAddFamilyMembersAndGetFamily() throws Exception {
        Gson gson = Converters.registerLocalDate(new GsonBuilder()).create();

        //        create family
        MockHttpServletResponse createFamilyResponse = mockMvc.perform(post(FAMILIES_URL))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn()
                .getResponse();
        long familyId = Long.decode(createFamilyResponse.getContentAsString());

        //        add father
        Father father = gson.fromJson(new FileReader(getClass().getClassLoader().getResource("father.json").getFile()), Father.class);
        mockMvc.perform(post(FAMILIES_URL + "/" + familyId + "/father")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(father)))
                .andExpect(status().isCreated())
                .andDo(print());
//        add children
        Child[] children = gson.fromJson(new FileReader(getClass().getClassLoader().getResource("children.json").getFile()), Child[].class);
        mockMvc.perform(post(FAMILIES_URL + "/" + familyId + "/children")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(children[0])))
                .andExpect(status().isCreated())
                .andDo(print());
        mockMvc.perform(post(FAMILIES_URL + "/" + familyId + "/children")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(children[1])))
                .andExpect(status().isCreated())
                .andDo(print());

//        read family
        MockHttpServletResponse getFamilyResponse = mockMvc.perform(get(FAMILIES_URL + "/" + familyId))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse();

        assertEquals(gson.toJson(new Family(father, Arrays.asList(children))), getFamilyResponse.getContentAsString());
    }

    private int getFamilyCount() {
        return jdbcTemplate.queryForObject(SELECT_COUNT_FROM_FAMILY_QUERY, Integer.class);
    }
}