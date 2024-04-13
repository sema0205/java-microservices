import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.CatRegistryApplication;
import org.example.cat.Breed;
import org.example.cat.Color;
import org.example.controller.CatController;
import org.example.dto.CatDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CatRegistryApplication.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PostMethodTest {
    public static final
    MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8);

    private CatController catController;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectWriter ow;

    public PostMethodTest() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        this.ow = mapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    public void correctlyRefreshDateAfterUpdate() throws Exception {

        var firstCat = new CatDto(
                null,
                "Whiskers",
                LocalDateTime.parse("2018-05-20T00:00"),
                Breed.SIAMESE,
                Color.WHITE,
                null,
                null
        );

        var secondCat = new CatDto(
                null,
                "Shadow",
                LocalDateTime.parse("2014-05-20T00:00"),
                Breed.SIAMESE,
                Color.WHITE,
                null,
                null
        );

        this.mockMvc.perform(
                        post("/api/v1/cats")
                                .contentType(APPLICATION_JSON_UTF8)
                                .content(ow.writeValueAsString(firstCat)))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(
                        post("/api/v1/cats")
                                .contentType(APPLICATION_JSON_UTF8)
                                .content(ow.writeValueAsString(secondCat)))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(
                        get("/api/v1/cats")
                                .contentType(APPLICATION_JSON_UTF8)
                                .content(ow.writeValueAsString(secondCat)))
                .andDo(print())
                .andExpect(status().isOk());


//        var secondImportItem = new ShopUnitImportRequest(
//                List.of(
//                        new ShopUnitImport(
//                                id,
//                                "тестовое имя по-русски",
//                                null,
//                                ShopUnitType.OFFER,
//                                1L
//                        )
//                ),
//                "2024-01-04T21:00:00.529Z"
//        );
//
//        this.mockMvc.perform(
//                        post("/imports")
//                                .contentType(APPLICATION_JSON_UTF8)
//                                .content(ow.writeValueAsString(secondImportItem)))
//                .andDo(print())
//                .andExpect(status().isOk());
//
//        this.mockMvc.perform(
//                        get("/nodes/" + id))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.date", is("2024-01-04T21:00:00.529Z")));
//
//        this.mockMvc.perform(
//                        get("/nodes/" + id))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.date", is("2024-01-05T21:00:00.000Z")));
    }

}