package pl.kancelaria.AHG.modules.categoriesRegulations.service;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import pl.kancelaria.AHG.common.entityModel.regulations.category.CategoryRegulationOB;
import pl.kancelaria.AHG.common.entityModel.regulations.category.repository.CategoryRegulationRepository;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTOrequest;
import pl.kancelaria.AHG.modules.categories.dto.CategoryListDTO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CategoryRegulationListService {

    private final CategoryRegulationRepository categoryRegulationRepository;

    public CategoryRegulationListService(CategoryRegulationRepository categoryRegulationRepository) {
        this.categoryRegulationRepository = categoryRegulationRepository;
    }

    public CategoryDTOrequest pobierzKategoriePoId(long id) {
        CategoryRegulationOB categoryRegulationOB = categoryRegulationRepository.getOne(id);
        CategoryDTOrequest categoryDTOrequest = new CategoryDTOrequest();
        BeanUtils.copyProperties(categoryRegulationOB, categoryDTOrequest);
        return categoryDTOrequest;
    }

    public CategoryListDTO pobierzListeKategorii() {
        CategoryListDTO response = new CategoryListDTO();
        List<CategoryRegulationOB> categoryRegulationOB = categoryRegulationRepository.findAll();
        if (!CollectionUtils.isEmpty(categoryRegulationOB)) {
            List<CategoryDTO> categoryList = new ArrayList<>();
            categoryRegulationOB.forEach(category -> {
                CategoryDTO categoryDTO = new CategoryDTO();
                BeanUtils.copyProperties(category, categoryDTO);
                categoryList.add(categoryDTO);
            });
            categoryList.sort(Comparator.comparing(CategoryDTO::getRodzajKategorii).reversed());
            response.setListaKategorii(categoryList);
        } else {
            response.setListaKategorii(new ArrayList<>());
        }
        return response;
    }

}
