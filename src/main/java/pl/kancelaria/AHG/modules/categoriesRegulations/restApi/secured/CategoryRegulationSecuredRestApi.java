package pl.kancelaria.AHG.modules.categoriesRegulations.restApi.secured;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTOrequest;
import pl.kancelaria.AHG.modules.categoriesRegulations.service.CategoryRegulationListService;
import pl.kancelaria.AHG.modules.categoriesRegulations.service.CreateCategoryRegulationService;
import pl.kancelaria.AHG.modules.categoriesRegulations.service.DeleteCategoryRegulationService;

@RestController
public class CategoryRegulationSecuredRestApi implements pl.kancelaria.AHG.shared.restapi.modules.categoriesRegulations.restApi.secured.CategoryRegulationSecuredRestApi {

    private final CreateCategoryRegulationService createCategoryRegulationService;
    private final DeleteCategoryRegulationService deleteCategoryRegulationService;
    private final CategoryRegulationListService categoryRegulationListService;

    public CategoryRegulationSecuredRestApi(CreateCategoryRegulationService createCategoryRegulationService,
                                            DeleteCategoryRegulationService deleteCategoryRegulationService,
                                            CategoryRegulationListService categoryRegulationListService) {
        this.createCategoryRegulationService = createCategoryRegulationService;
        this.deleteCategoryRegulationService = deleteCategoryRegulationService;
        this.categoryRegulationListService = categoryRegulationListService;
    }

    @Override
    public ResponseEntity<HttpStatus> dodajKategorie(CategoryDTO categoryDTO) {
         createCategoryRegulationService.dodajNowaKategorie(categoryDTO);
         return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> usunKategorie(long id) {
        deleteCategoryRegulationService.usunKategorie(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public CategoryDTOrequest szczegolyKategorii(long id) {
        return categoryRegulationListService.pobierzKategoriePoId(id);
    }
}
