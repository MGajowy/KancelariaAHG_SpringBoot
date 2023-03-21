package pl.kancelaria.AHG.shared.restapi.modules.categoriesRegulations.restApi.secured;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.kancelaria.AHG.common.entityModel.regulations.category.CategoryRegulationOB;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTOrequest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryRegulationSecuredRestApiTest {

    public static final String PATH_CATEGORY_REGULATION = CategoryRegulationSecuredRestApiUrl.SCIEZKA_KATEGORIE_ROZPORZADZEN;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EntityManager entityManager;

    @Test
    void shouldCreateNewCategory() throws Exception {
        // given
        // when
        mockMvc.perform(MockMvcRequestBuilders.post(PATH_CATEGORY_REGULATION + CategoryRegulationSecuredRestApiUrl.DODAJ_KATEGORIE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new CategoryDTO(1, true, "KategoriaRozporzadzenia")))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(201))
                .andReturn();
    }

    @Test
    @Transactional
    void shouldDeleteCategoryRegulation() throws Exception {
        // given
        CategoryRegulationOB categoryRegulation = createCategoryRegulation();
        // when
        mockMvc.perform(MockMvcRequestBuilders.delete(PATH_CATEGORY_REGULATION + CategoryRegulationSecuredRestApiUrl.USUN_KATEGORIE + "/{id}", categoryRegulation.getId()))
                .andExpect(status().is2xxSuccessful());
        // then
    }

    @Test
    @Transactional
    void shouldModifyCategoryRegulation() throws Exception {
        // given
        CategoryRegulationOB categoryRegulation = createCategoryRegulation();
        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(PATH_CATEGORY_REGULATION + CategoryRegulationSecuredRestApiUrl.MODYFIKUJ_KATEGORIE + "/{id}", categoryRegulation.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new CategoryDTOrequest(1, false, "TODO")))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        // then
        CategoryDTOrequest response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), CategoryDTOrequest.class);
        assertThat(response.getCategoryName()).isEqualTo(categoryRegulation.getCategoryName());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private CategoryRegulationOB createCategoryRegulation() {
        CategoryRegulationOB categoriesOB = new CategoryRegulationOB();
        categoriesOB.setIsPublic(Boolean.FALSE);
        categoriesOB.setCategoryName("NowaKategoria");
        return entityManager.merge(categoriesOB);
    }
}