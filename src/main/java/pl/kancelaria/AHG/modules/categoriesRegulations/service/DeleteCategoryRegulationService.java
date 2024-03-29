package pl.kancelaria.AHG.modules.categoriesRegulations.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.common.entityModel.regulations.category.repository.CategoryRegulationRepository;

@Service
public class DeleteCategoryRegulationService {

    private final CategoryRegulationRepository categoryRegulationRepository;

    public DeleteCategoryRegulationService(CategoryRegulationRepository categoryRegulationRepository) {
        this.categoryRegulationRepository = categoryRegulationRepository;
    }

    public ResponseEntity<HttpStatus> deleteCategories(long id) {
        try {
            categoryRegulationRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
