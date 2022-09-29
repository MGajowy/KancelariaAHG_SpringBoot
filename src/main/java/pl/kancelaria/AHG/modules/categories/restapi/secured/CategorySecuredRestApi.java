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
    public ResponseEntity<HttpStatus> dodajKategorie(CategoryDTO categoryDTO) {
       return createCategoryService.dodajNowaKategorie(categoryDTO);
    }

    @Override
    public CategoryDTOrequest modyfikujKategorie(long id, CategoryDTOrequest request) {
        return this.modifyCategoryService.modyfikujKategorie(id, request);
    }

    @Override
    public ResponseEntity<HttpStatus> usunKategorie(@PathVariable("id") long id) {
       return deleteCategoryService.usunKategorie(id);
    }

    @Override
    public CategoryDTOrequest szczegolyKategorii(long id) {
        return categoryListService.pobierzKategoriePoId(id);
    }

    @Override
    public CategoryListDTO listaKategoriiPoStatusie(Boolean status) {
        return categoryListService.wyszukajKategoriePoStatusie(status);
    }
}
