package pl.kancelaria.AHG.modules.categoriesRegulations.restApi.secured;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTOrequest;
import pl.kancelaria.AHG.modules.categoriesRegulations.service.CategoryRegulationListService;
import pl.kancelaria.AHG.modules.categoriesRegulations.service.CreateCategoryRegulationService;
import pl.kancelaria.AHG.modules.categoriesRegulations.service.DeleteCategoryRegulationService;
import pl.kancelaria.AHG.modules.categoriesRegulations.service.ModifyCategoryRegulationService;

@RestController
public class CategoryRegulationSecuredRestApi implements pl.kancelaria.AHG.shared.restapi.modules.categoriesRegulations.restApi.secured.CategoryRegulationSecuredRestApi {

    private final CreateCategoryRegulationService createCategoryRegulationService;
    private final DeleteCategoryRegulationService deleteCategoryRegulationService;
    private final CategoryRegulationListService categoryRegulationListService;
    private final ModifyCategoryRegulationService modifyCategoryRegulationService;

    public CategoryRegulationSecuredRestApi(CreateCategoryRegulationService createCategoryRegulationService,
                                            DeleteCategoryRegulationService deleteCategoryRegulationService,
                                            CategoryRegulationListService categoryRegulationListService,
                                            ModifyCategoryRegulationService modifyCategoryRegulationService) {
        this.createCategoryRegulationService = createCategoryRegulationService;
        this.deleteCategoryRegulationService = deleteCategoryRegulationService;
        this.categoryRegulationListService = categoryRegulationListService;
        this.modifyCategoryRegulationService = modifyCategoryRegulationService;
    }

    @Override
    public ResponseEntity<HttpStatus> dodajKategorieRozporzadzenia(CategoryDTO categoryDTO) {
        return createCategoryRegulationService.addNewCategories(categoryDTO);
    }

    @Override
    public ResponseEntity<HttpStatus> usunKategorieRozporzadzenia(long id) {
        return deleteCategoryRegulationService.deleteCategories(id);
    }

    @Override
    public CategoryDTOrequest szczegolyKategoriiRozporzadzenia(long id) {
        return categoryRegulationListService.getCategoriesById(id);
    }

    @Override
    public CategoryDTOrequest modyfikujKategorieRozporzadzenia(long id, CategoryDTOrequest request) {
        return modifyCategoryRegulationService.modifyCategories(id, request);
    }
}
