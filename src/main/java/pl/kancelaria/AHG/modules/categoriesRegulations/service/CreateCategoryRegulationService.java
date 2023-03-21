package pl.kancelaria.AHG.modules.categoriesRegulations.service;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.common.entityModel.regulations.category.CategoryRegulationOB;
import pl.kancelaria.AHG.common.entityModel.regulations.category.repository.CategoryRegulationRepository;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;

import javax.transaction.Transactional;

@Service
public class CreateCategoryRegulationService {

    private final CategoryRegulationRepository categoryRegulationRepository;

    public CreateCategoryRegulationService(CategoryRegulationRepository categoryRegulationRepository) {
        this.categoryRegulationRepository = categoryRegulationRepository;
    }

    @Transactional
    public ResponseEntity<HttpStatus> addNewCategory(CategoryDTO categoryDTO) {
        if (categoryDTO.getCategoryName() != null) {
            CategoryRegulationOB categoryRegulationOB = new CategoryRegulationOB();
            BeanUtils.copyProperties(categoryDTO, categoryRegulationOB);
            if (categoryDTO.getIsPublic() == null)
                categoryRegulationOB.setIsPublic(false);
            categoryRegulationRepository.save(categoryRegulationOB);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
