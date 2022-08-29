package pl.kancelaria.AHG.modules.categoriesRegulations.service;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.kancelaria.AHG.common.entityModel.regulations.category.CategoryRegulationOB;
import pl.kancelaria.AHG.common.entityModel.regulations.category.repository.CategoryRegulationRepository;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;

public class CreateCategoryRegulationService {

    private final CategoryRegulationRepository categoryRegulationRepository;

    public CreateCategoryRegulationService(CategoryRegulationRepository categoryRegulationRepository) {
        this.categoryRegulationRepository = categoryRegulationRepository;
    }

    public ResponseEntity<HttpStatus> dodajNowaKategorie(CategoryDTO categoryDTO) {
        if (categoryDTO.getRodzajKategorii() != null) {
            CategoryRegulationOB categoryRegulationOB = new CategoryRegulationOB();
            BeanUtils.copyProperties(categoryDTO, categoryRegulationOB);
            if (categoryDTO.getCzyPubliczny() == null) {
                categoryRegulationOB.setCzyPubliczny(false);
                categoryRegulationRepository.save(categoryRegulationOB);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
}
