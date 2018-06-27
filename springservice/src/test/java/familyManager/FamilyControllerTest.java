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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileNotFoundException;
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
    private final String FAMILIES_URL = "http://localhost:8081/families";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    private Gson jsonConverter;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        jsonConverter = Converters.registerLocalDate(new GsonBuilder()).create();
    }

    @Test
    public void shouldAddFamilyMembersAndGetFamily() throws Exception {
        Father father = readFromFile("father.json", Father.class);
        Child[] children = readFromFile("children.json", Child[].class);
        Family family = new Family(father, Arrays.asList(children));

        long familyId = createFamily();

        createFather(familyId, father);

        createChild(familyId, children[0]);
        createChild(familyId, children[1]);

        String familyAsJson = readFamilyAsJson(familyId);

        assertEquals(jsonConverter.toJson(family), familyAsJson);
    }

    private <T> T readFromFile(String fileName, Class<T> classOfT) throws FileNotFoundException {
        return jsonConverter.fromJson(new FileReader(getClass().getClassLoader().getResource(fileName).getFile()), classOfT);
    }

    private long createFamily() throws Exception {
        MockHttpServletResponse createFamilyResponse = mockMvc.perform(post(FAMILIES_URL))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn()
                .getResponse();
        return Long.decode(createFamilyResponse.getContentAsString());
    }

    private void createFather(long familyId, Father father) throws Exception {
        mockMvc.perform(post(FAMILIES_URL + "/" + familyId + "/father")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonConverter.toJson(father)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    private void createChild(long familyId, Child child) throws Exception {
        mockMvc.perform(post(FAMILIES_URL + "/" + familyId + "/children")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonConverter.toJson(child)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    private String readFamilyAsJson(long familyId) throws Exception {
        return mockMvc.perform(get(FAMILIES_URL + "/" + familyId))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}