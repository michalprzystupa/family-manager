package familyManager;

import org.junit.Ignore;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = Application.class)
//@WebAppConfiguration
@Ignore
public class AcceptanceTest {
//    private final String FAMILIES_URL = "http://localhost:8081/families";
//
//    private Gson gson = new GsonBuilder().create();
//
//    @Autowired
//    WebApplicationContext context;
//
//    MockMvc mockMvc;
//
//    @Before
//    public void setUp() throws Exception {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
//
//    @Test
//    public void shouldPass() {
//        assert true;
//    }
//
//    @Test
//    public void name() throws Exception {
//        Father father = new Father(12, LocalDate.of(2015, 2, 20), "Jan", "12345678910", "Kowalski");
//        Child child1 = new Child(3, "male", "Adam", "1234567890", "Kowalski");
//        Child child2 = new Child(3, "female", "Anna", "1234567890", "Kowalski");
//        Family family = new Family(father, Arrays.asList(child1, child2));
//
//        mockMvc.perform(post(FAMILIES_URL))
//                .andDo(print());
//        mockMvc.perform(post(FAMILIES_URL + "/1/father")
//                .contentType(APPLICATION_JSON)
//                .content(gson.toJson(father)))
//                .andDo(print());
////        mockMvc.perform(post(FAMILIES_URL + "/1/children")
////                .content(gson.toJson(child1)))
////                .andDo(print());
////        mockMvc.perform(post(FAMILIES_URL + "/1/children")
////                .content(gson.toJson(child2)))
////                .andDo(print());
////        mockMvc.perform(get(FAMILIES_URL))
////                .andDo(print())
////                .andExpect(content().json(gson.toJson(family)));
//
//
////                .andExpect(content().string("1"));
//    }
}