package pl.kancelaria.AHG.modules.categories.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.CategoriesOB;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.repository.CategoriesRepository;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;


@Service
public class CreateCategoryService {

    private final CategoriesRepository categoriesRepository;

    public CreateCategoryService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public Boolean dodajNowaKategorie(CategoryDTO categoryDTO) {
        if (categoryDTO != null) {
            CategoriesOB categoriesOB = new CategoriesOB();
            BeanUtils.copyProperties(categoryDTO, categoriesOB);
            if (categoryDTO.getCzyPubliczny() == null)
                categoriesOB.setCzyPubliczny(false);
            categoriesRepository.save(categoriesOB);
            return true;
        }
        return false;
    }
}
