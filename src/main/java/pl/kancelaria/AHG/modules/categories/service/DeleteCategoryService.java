package pl.kancelaria.AHG.modules.categories.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.repository.CategoriesRepository;


@Service
public class DeleteCategoryService {

    private final CategoriesRepository categoriesRepository;

    public DeleteCategoryService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public ResponseEntity<HttpStatus> usunKategorie(long id) {
        try {
            categoriesRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
