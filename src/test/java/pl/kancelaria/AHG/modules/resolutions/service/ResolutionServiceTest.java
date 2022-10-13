package pl.kancelaria.AHG.modules.resolutions.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.CategoriesOB;
import pl.kancelaria.AHG.common.entityModel.resolutions.resolutions.ResolutionsOB;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionListDTO;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ResolutionServiceTest {

    @Mock
    public EntityManager entityManager;

    @Mock
    public CriteriaBuilder cb;

    @InjectMocks
    private ResolutionService resolutionService;

//    @Test
    void shouldReturnResolutionList() {
        // given
        // when
        ResolutionListDTO list = resolutionService.getResolutionListCB();

        // then
        assertThat(list).isNotNull();

    }

    private List<ResolutionsOB> createResolutionList() {
        return Arrays.asList(
                new ResolutionsOB(1L, "nowa1", "nowa1", true, new CategoriesOB()),
                new ResolutionsOB(2L, "nowa2", "nowa2", true, new CategoriesOB()),
                new ResolutionsOB(3L, "nowa3", "nowa3", false, new CategoriesOB())
        );
    }
}