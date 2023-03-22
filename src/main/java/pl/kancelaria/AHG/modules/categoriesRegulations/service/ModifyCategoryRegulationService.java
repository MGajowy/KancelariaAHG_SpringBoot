package pl.kancelaria.AHG.modules.categoriesRegulations.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.common.entityModel.regulations.category.CategoryRegulationOB;
import pl.kancelaria.AHG.common.entityModel.regulations.category.repository.CategoryRegulationRepository;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTOrequest;

@Service
public class ModifyCategoryRegulationService {

    private final CategoryRegulationRepository categoryRegulationRepository;

    public ModifyCategoryRegulationService(CategoryRegulationRepository categoryRegulationRepository) {
        this.categoryRegulationRepository = categoryRegulationRepository;
    }

    public CategoryDTOrequest modifyCategories(long id, CategoryDTOrequest request) {
        CategoryRegulationOB categoryRegulationOB = categoryRegulationRepository.getOne(id);
        categoryRegulationOB.setIsPublic(request.getIsPublic());
        categoryRegulationOB.setCategoryName(request.getCategoryName());
        categoryRegulationRepository.save(categoryRegulationOB);
        BeanUtils.copyProperties(categoryRegulationOB, request);
        return request;
    }
}
