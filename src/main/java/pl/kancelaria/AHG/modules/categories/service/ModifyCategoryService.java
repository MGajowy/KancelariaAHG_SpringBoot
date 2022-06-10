package pl.kancelaria.AHG.modules.categories.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.comon.model.resolutions.categories.CategoriesOB;
import pl.kancelaria.AHG.comon.model.resolutions.categories.repository.CategoriesRepository;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTOrequest;

/**
 * @author Michal
 * @created 28/08/2020
 */
@Service
public class ModifyCategoryService {

    private final CategoriesRepository categoriesRepository;

    public ModifyCategoryService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public CategoryDTOrequest modyfikujKategorie(long id, CategoryDTOrequest request) {
//        Optional<CategoriesOB> dataCategory = this.categoriesRepository.findById(id);
        //if (data.isPresent()){
        CategoriesOB categoriesOB = this.categoriesRepository.getOne(id);
              categoriesOB.setCzyPubliczny(request.getCzyPubliczny());
              categoriesOB.setRodzajKategorii(request.getRodzajKategorii());
//            response.setId(request.getId());
            this.categoriesRepository.save(categoriesOB);
            BeanUtils.copyProperties(categoriesOB, request);
            return request;
      //  }

    }
}
