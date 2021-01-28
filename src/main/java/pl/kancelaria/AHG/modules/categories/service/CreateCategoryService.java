package pl.kancelaria.AHG.modules.categories.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.comon.model.resolutions.categories.CategoriesOB;
import pl.kancelaria.AHG.comon.model.resolutions.categories.repository.CategoriesRepository;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;

/**
 * @author Michal
 * @created 28/08/2020
 */
@Service
public class CreateCategoryService {

    private final CategoriesRepository categoriesRepository;

    public CreateCategoryService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public CategoryDTO dodajNowaKategorie(CategoryDTO categoryDTO) {
        CategoryDTO wynik = new CategoryDTO();
        CategoriesOB categoriesOB = new CategoriesOB();
        BeanUtils.copyProperties(categoryDTO, categoriesOB);
        this.categoriesRepository.save(categoriesOB);
        return wynik;
    }
}
