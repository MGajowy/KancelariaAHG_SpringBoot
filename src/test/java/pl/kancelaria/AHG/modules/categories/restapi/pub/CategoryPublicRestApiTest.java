package pl.kancelaria.AHG.modules.categories.restapi.pub;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.kancelaria.AHG.shared.restapi.modules.categories.restapi.pub.CategoryPublicRestApiUrl;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryPublicRestApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldSearchCategoryList() throws Exception {
        //given
        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(CategoryPublicRestApiUrl.SCIEZKA_KATEGORIE + CategoryPublicRestApiUrl.WSZYSTKIE_KATEGORIE))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
    }
}