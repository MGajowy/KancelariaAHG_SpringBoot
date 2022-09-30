package pl.kancelaria.AHG.modules.categories.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.CategoriesOB;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.repository.CategoriesRepository;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTOrequest;
import pl.kancelaria.AHG.modules.categories.dto.CategoryListDTO;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryListServiceTest {

    public static final long ID = 1;

    @Mock
    public EntityManager entityManager;
    @Mock
    private CategoriesRepository categoriesRepository;
    @InjectMocks
    private CategoryListService categoryListService;

    @Test
    void shouldReturnCategoriesList() {
        // given
        when(categoriesRepository.findAll()).thenReturn(createCategoryList());
        // when
        CategoryListDTO categoryListDTO = categoryListService.pobierzListeKategorii();
        List<CategoryDTO> listaKategorii = categoryListDTO.getListaKategorii();
        // then
        assertThat(categoryListDTO.getListaKategorii()).isNotNull();
        assertThat(categoryListDTO.getListaKategorii()).hasSize(4);
        assertThat(listaKategorii).extracting("czyPubliczny").contains(true);
    }

    @Test
    void shouldReturnCategoriesListById() {
        // given
       when(categoriesRepository.getOne(ID)).thenReturn(createCategoryList().get(0));
        // when
        CategoryDTOrequest categoryDTOrequest = categoryListService.pobierzKategoriePoId(ID);
        // then
        assertThat(categoryDTOrequest).isNotNull();
        assertThat(categoryDTOrequest.getId()).isEqualTo(ID);
    }

    @Test
    void shouldReturnCategoriesByName() {
        // given
        when(categoriesRepository.findCategoriesByRodzajKategoriiImpl("NOWA")).thenReturn(createCategoryList());
        // when
        List<String> actual = categoryListService.pobierzListeKategoriiPoNazwie("NOWA");
        // then
        assertThat(actual.get(0)).isEqualTo("NOWA");
        assertThat(actual).hasSize(2);
    }

    private List<CategoriesOB> createCategoryList() {
        return Arrays.asList(
                new CategoriesOB(1L, "NOWA", true),
                new CategoriesOB(2L, "Publiczna", false),
                new CategoriesOB(3L, "Inna", true),
                new CategoriesOB(4L, "NOWA", false)
        );
    }
}