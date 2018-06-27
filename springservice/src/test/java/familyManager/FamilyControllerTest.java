package familyManager;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import familyManager.api.Child;
import familyManager.api.Family;
import familyManager.api.Father;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FamilyControllerTest {
    private final String FAMILIES_URL = "http://localhost:8081/families";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    private Gson jsonConverter;
    private Father[] allFathers;
    private Child[] allChildren;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        jsonConverter = Converters.registerLocalDate(new GsonBuilder()).create();
        allFathers = readFromFile("fathers.json", Father[].class);
        allChildren = readFromFile("children.json", Child[].class);
    }

    @Test
    public void shouldSearchFamiliesByChildParameters() throws Exception {
        List<Child> children1 = Collections.singletonList(allChildren[0]);
        List<Child> children2 = Arrays.asList(allChildren[1], allChildren[2]);
        List<Child> children3 = Arrays.asList(allChildren[3], allChildren[4], allChildren[5]);
        List<Child> children4 = Collections.emptyList();

        Father father1 = allFathers[0];
        Father father2 = allFathers[1];
        Father father3 = allFathers[2];
        Father father4 = allFathers[3];

        Family family1 = new Family(father1, children1);
        Family family2 = new Family(father2, children2);
        Family family3 = new Family(father3, children3);
        Family family4 = new Family(father4, children4);

        createFullFamily(allFathers[0], children1);
        createFullFamily(allFathers[1], children2);
        createFullFamily(allFathers[2], children3);
        createFullFamily(allFathers[3], children4);

        String searchFemale = searchFamilyAsJson("", "", "", "female");
        String searchByFirstName = searchFamilyAsJson("gor", "", "", "");
        String searchBySecondName = searchFamilyAsJson("", "Ronaldo", "", "");
        String searchByPesel = searchFamilyAsJson("", "", "1", "");
        String searchWithEmptyParams = searchFamilyAsJson("", "", "", "");
        String searchWithoutParameters = mockMvc.perform(get(FAMILIES_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(jsonConverter.toJson(Arrays.asList(family2, family3)), searchFemale);
        assertEquals(jsonConverter.toJson(Collections.singletonList(family1)), searchByFirstName);
        assertEquals(jsonConverter.toJson(Collections.emptyList()), searchBySecondName);
        assertEquals(jsonConverter.toJson(Arrays.asList(family1, family2, family3)), searchByPesel);
        assertEquals(jsonConverter.toJson(Arrays.asList(family1, family2, family3)), searchWithEmptyParams);
        assertEquals(jsonConverter.toJson(Arrays.asList(family1, family2, family3)), searchWithoutParameters);
    }

    @Test
    public void shouldReadFather() throws Exception {
        Father father = allFathers[0];

        long familyId = createFamily();
        createFather(familyId, father);

        String fatherAsJson = readFatherAsJson(familyId);

        assertEquals(jsonConverter.toJson(father), fatherAsJson);
    }

    @Test
    public void shouldReadChildren() throws Exception {
        List<Child> children = Arrays.asList(allChildren[0], allChildren[1]);

        long familyId = createFamily();
        createChild(familyId, children.get(0));
        createChild(familyId, children.get(1));

        String childrenAsJson = readChildrenAsJson(familyId);

        assertEquals(jsonConverter.toJson(children), childrenAsJson);
    }

    @Test
    public void shouldReadFamily() throws Exception {
        Father father = allFathers[0];
        List<Child> children = Arrays.asList(allChildren[0], allChildren[1]);
        Family family = new Family(father, children);

        long familyId = createFullFamily(father, children);

        String familyAsJson = readFamilyAsJson(familyId);

        assertEquals(jsonConverter.toJson(family), familyAsJson);
    }

    private String searchFamilyAsJson(String firstName, String secondName, String pesel, String sex) throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("firstName", firstName);
        params.add("secondName", secondName);
        params.add("pesel", pesel);
        params.add("sex", sex);

        return mockMvc.perform(get(FAMILIES_URL).params(params))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
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

    private long createFullFamily(Father father, List<Child> children) throws Exception {
        long familyId = createFamily();

        createFather(familyId, father);

        for (Child child : children) {
            createChild(familyId, child);
        }

        return familyId;
    }

    private String readFatherAsJson(long familyId) throws Exception {
        return mockMvc.perform(get(FAMILIES_URL + "/" + familyId + "/father"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    private String readChildrenAsJson(long familyId) throws Exception {
        return mockMvc.perform(get(FAMILIES_URL + "/" + familyId + "/children"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    private String readFamilyAsJson(long familyId) throws Exception {
        return mockMvc.perform(get(FAMILIES_URL + "/" + familyId))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    private <T> T readFromFile(String fileName, Class<T> classOfT) throws FileNotFoundException {
        return jsonConverter.fromJson(new FileReader(getClass().getClassLoader().getResource(fileName).getFile()), classOfT);
    }
}