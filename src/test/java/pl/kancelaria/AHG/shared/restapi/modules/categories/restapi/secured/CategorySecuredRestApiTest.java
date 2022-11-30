package pl.kancelaria.AHG.shared.restapi.modules.categories.restapi.secured;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.CategoriesOB;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTOrequest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CategorySecuredRestApiTest {

    public static final String PATH_CATEGORY = CategorySecuredRestApiUrl.SCIEZKA_KATEGORIE;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateNewCategory() throws Exception {
        // given
        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(PATH_CATEGORY + CategorySecuredRestApiUrl.DODAJ_KATEGORIE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new CategoryDTO(1, true, "Nowa")))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(201))
                .andReturn();
    }

    @Test
    @Transactional
    void shouldModifyCategory() throws Exception {
        // given
        CategoriesOB category = createCategory();
        // when
        mockMvc.perform(MockMvcRequestBuilders.put(PATH_CATEGORY + CategorySecuredRestApiUrl.MODYFIKUJ_KATEGORIE + "/{id}", category.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new CategoryDTOrequest(category.getId(), true, "TODO")))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
    }

    @Test
    @Transactional
    void shouldDeleteCategory() throws Exception {
        // given
        CategoriesOB category = createCategory();
        // when
        mockMvc.perform(MockMvcRequestBuilders.delete(PATH_CATEGORY + CategorySecuredRestApiUrl.USUN_KATEGORIE + "/{id}", category.getId()))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @Transactional
    void shouldDetailsCategory() throws Exception {
        // given
        CategoriesOB category = createCategory();
        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(PATH_CATEGORY + CategorySecuredRestApiUrl.SZCZEGOLY_KATEGORII + "/{id}", category.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        // then
        CategoryDTOrequest response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), CategoryDTOrequest.class);
        assertThat(response.getRodzajKategorii()).isEqualTo(category.getRodzajKategorii());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private CategoriesOB createCategory() {
        CategoriesOB categoriesOB = new CategoriesOB();
        categoriesOB.setCzyPubliczny(Boolean.FALSE);
        categoriesOB.setRodzajKategorii("DOTO");
        return entityManager.merge(categoriesOB);
    }
}