package pl.kancelaria.AHG.modules.categories.restapi.pub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import pl.kancelaria.AHG.modules.categories.dto.CategoryListDTO;
import pl.kancelaria.AHG.modules.categories.service.CategoryListService;


@RestController
public class CategoryPublicRestApi implements pl.kancelaria.AHG.shared.restapi.modules.categories.restapi.pub.CategoryPublicRestApi {

    private final CategoryListService categoryListService;

    @Autowired
    public CategoryPublicRestApi(CategoryListService categoryListService) {
        this.categoryListService = categoryListService;
    }

    @Override
    public CategoryListDTO getCategoryList() {
        return categoryListService.getCategoryList();
    }

    @Override
    public CategoryListDTO searchCategories(String term) {
        return categoryListService.searchCategories(term);
    }

}
