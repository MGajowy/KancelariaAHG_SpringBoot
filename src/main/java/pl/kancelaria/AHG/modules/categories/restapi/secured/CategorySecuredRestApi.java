package pl.kancelaria.AHG.modules.categories.restapi.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTOrequest;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTOresponse;
import pl.kancelaria.AHG.modules.categories.service.CreateCategoryService;
import pl.kancelaria.AHG.modules.categories.service.DeleteCategoryService;
import pl.kancelaria.AHG.modules.categories.service.ModifyCategoryService;

/**
 * @author Michal
 * @created 28/08/2020
 */
@RestController
public class CategorySecuredRestApi implements pl.kancelaria.AHG.shared.restapi.modules.categories.restapi.secured.CategorySecuredRestApi {

    private final CreateCategoryService createCategoryService;
    private final DeleteCategoryService deleteCategoryService;
    private final ModifyCategoryService modifyCategoryService;
    private CategoryDTO categoryDTO;

    @Autowired
    public CategorySecuredRestApi(CreateCategoryService createCategoryService, DeleteCategoryService deleteCategoryService, ModifyCategoryService modifyCategoryService) {
        this.createCategoryService = createCategoryService;
        this.deleteCategoryService = deleteCategoryService;
        this.modifyCategoryService = modifyCategoryService;
    }

    @Override
    public ResponseEntity<HttpStatus> dodajKategorie(CategoryDTO categoryDTO) {
    createCategoryService.dodajNowaKategorie(categoryDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public CategoryDTOresponse modyfikujKategorie(CategoryDTOrequest request) {
        return this.modifyCategoryService.modyfikujKategorie(request);
    }

    @Override
    public ResponseEntity<HttpStatus> usunKategorie(@PathVariable("id") long id) {
    deleteCategoryService.usunKategorie(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
