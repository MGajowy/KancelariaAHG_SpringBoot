package pl.kancelaria.AHG.modules.categories.restapi.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTOrequest;
import pl.kancelaria.AHG.modules.categories.dto.CategoryListDTO;
import pl.kancelaria.AHG.modules.categories.service.CategoryListService;
import pl.kancelaria.AHG.modules.categories.service.CreateCategoryService;
import pl.kancelaria.AHG.modules.categories.service.DeleteCategoryService;
import pl.kancelaria.AHG.modules.categories.service.ModifyCategoryService;


@RestController
public class CategorySecuredRestApi implements pl.kancelaria.AHG.shared.restapi.modules.categories.restapi.secured.CategorySecuredRestApi {

    private final CreateCategoryService createCategoryService;
    private final DeleteCategoryService deleteCategoryService;
    private final ModifyCategoryService modifyCategoryService;
    private final CategoryListService categoryListService;

    @Autowired
    public CategorySecuredRestApi(CreateCategoryService createCategoryService,
                                  DeleteCategoryService deleteCategoryService,
                                  ModifyCategoryService modifyCategoryService,
                                  CategoryListService categoryListService) {
        this.createCategoryService = createCategoryService;
        this.deleteCategoryService = deleteCategoryService;
        this.modifyCategoryService = modifyCategoryService;
        this.categoryListService = categoryListService;
    }

    @Override
    public ResponseEntity<HttpStatus> addNewCategories(CategoryDTO categoryDTO) {
       return createCategoryService.addNewCategories(categoryDTO);
    }

    @Override
    public CategoryDTOrequest modifyCategories(long id, CategoryDTOrequest request) {
        return this.modifyCategoryService.modifyCategories(id, request);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteCategories(@PathVariable("id") long id) {
       return deleteCategoryService.deleteCategories(id);
    }

    @Override
    public CategoryDTOrequest getCategoriesById(long id) {
        return categoryListService.getCategoriesById(id);
    }

    @Override
    public CategoryListDTO searchCategoriesByStatus(Boolean status) {
        return categoryListService.searchCategoriesByStatus(status);
    }
}
