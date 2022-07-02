package pl.kancelaria.AHG.modules.categories.restapi.pub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import pl.kancelaria.AHG.modules.categories.dto.CategoryListDTO;
import pl.kancelaria.AHG.modules.categories.service.CategoryListService;

import java.util.List;


@RestController
public class CategoryPublicRestApi implements pl.kancelaria.AHG.shared.restapi.modules.categories.restapi.pub.CategoryPublicRestApi {

    private final CategoryListService categoryListService;

    @Autowired
    public CategoryPublicRestApi(CategoryListService categoryListService) {
        this.categoryListService = categoryListService;
    }

    @Override
    public String getStr() {
        return "Test Michal";
    }

    @Override
    public CategoryListDTO pobierzListCategoryDto() {
        return categoryListService.pobierzListeKategorii();
    }

    @Override
    public CategoryListDTO wyszukajKategorie(String term) {
        return categoryListService.wyszukajKategorie(term);
    }

    @Override
    public List<String> wyszukajKategoriePoNazwa(String rodzajKategorii) {
        return categoryListService.pobierzListeKategoriiPoNazwie2(rodzajKategorii);
    }
}
