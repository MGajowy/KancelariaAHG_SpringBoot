package pl.kancelaria.AHG.modules.categoriesRegulations.restApi.pub;

import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.modules.categories.dto.CategoryListDTO;
import pl.kancelaria.AHG.modules.categoriesRegulations.service.CategoryRegulationListService;

@RestController
public class CategoryRegulationPublicRestApi implements pl.kancelaria.AHG.shared.restapi.modules.categoriesRegulations.restApi.pub.CategoryRegulationPublicRestApi {

    private final CategoryRegulationListService categoryRegulationListService;

    public CategoryRegulationPublicRestApi(CategoryRegulationListService categoryRegulationListService) {
        this.categoryRegulationListService = categoryRegulationListService;
    }

    @Override
    public CategoryListDTO pobierzListeKategorii() {
        return categoryRegulationListService.getCategoryList();
    }

    @Override
    public CategoryListDTO wyszukajKategorieRozporzadzenPoNazwie(String term) {
        return categoryRegulationListService.getCategoryListByName(term);
    }
}
