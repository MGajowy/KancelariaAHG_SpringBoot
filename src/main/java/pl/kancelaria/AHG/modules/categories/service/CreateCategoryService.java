package pl.kancelaria.AHG.modules.categories.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.CategoriesOB;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.repository.CategoriesRepository;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;


@Service
public class CreateCategoryService {

    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CreateCategoryService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public ResponseEntity <HttpStatus> addNewCategories(CategoryDTO categoryDTO) {
        if (categoryDTO.getCategoryName() != null) {
            CategoriesOB categoriesOB = new CategoriesOB();
            BeanUtils.copyProperties(categoryDTO, categoriesOB);
            if (categoryDTO.getIsPublic() == null)
                categoriesOB.setIsPublic(false);
            categoriesRepository.save(categoriesOB);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
