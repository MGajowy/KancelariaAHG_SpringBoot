package pl.kancelaria.AHG.modules.categories.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.CategoriesOB;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.repository.CategoriesRepository;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTOrequest;


@Service
public class ModifyCategoryService {

    private final CategoriesRepository categoriesRepository;

    public ModifyCategoryService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public CategoryDTOrequest modifyCategories(long id, CategoryDTOrequest request) {
        CategoriesOB categoriesOB = this.categoriesRepository.getOne(id);
        categoriesOB.setIsPublic(request.getIsPublic());
        categoriesOB.setCategoryName(request.getCategoryName());
        this.categoriesRepository.save(categoriesOB);
        BeanUtils.copyProperties(categoriesOB, request);
        return request;
    }
}
