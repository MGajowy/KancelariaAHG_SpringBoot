package pl.kancelaria.AHG.modules.categories.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.repository.CategoriesRepository;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CreateCategoryServiceTest {

    @Mock
    CategoriesRepository categoriesRepository;

    @InjectMocks
    CreateCategoryService createCategoryService;

    @Test
    void shouldAddCategory() {
        // given
        CategoryDTO category = new CategoryDTO();
        category.setCzyPubliczny(true);
        category.setId(1L);
        category.setRodzajKategorii("nowa");

        // when
        ResponseEntity<HttpStatus> actual = createCategoryService.addNewCategories(category);

        // then
        assertThat(actual).isNotNull();
        assertThat(actual.getStatusCodeValue()).isEqualTo(201);
    }
}