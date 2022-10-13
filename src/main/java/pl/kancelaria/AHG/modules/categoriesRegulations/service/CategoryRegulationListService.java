package pl.kancelaria.AHG.modules.categoriesRegulations.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.kancelaria.AHG.common.entityModel.regulations.category.CategoryRegulationOB;
import pl.kancelaria.AHG.common.entityModel.regulations.category.repository.CategoryRegulationRepository;
import pl.kancelaria.AHG.common.entityModel.regulations.regulation.RegulationOB;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTOrequest;
import pl.kancelaria.AHG.modules.categories.dto.CategoryListDTO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CategoryRegulationListService {

    private final CategoryRegulationRepository categoryRegulationRepository;
    private final EntityManager entityManager;

    public CategoryRegulationListService(CategoryRegulationRepository categoryRegulationRepository,
                                         EntityManager entityManager) {
        this.categoryRegulationRepository = categoryRegulationRepository;
        this.entityManager = entityManager;
    }

    public CategoryDTOrequest getCategoriesById(long id) {
        CategoryRegulationOB categoryRegulationOB = categoryRegulationRepository.getOne(id);
        CategoryDTOrequest categoryDTOrequest = new CategoryDTOrequest();
        BeanUtils.copyProperties(categoryRegulationOB, categoryDTOrequest);
        return categoryDTOrequest;
    }

    public CategoryListDTO getCategoryList() {
        CategoryListDTO response = new CategoryListDTO();
        List<CategoryRegulationOB> categoryRegulationOB = categoryRegulationRepository.findAll();
        if (!CollectionUtils.isEmpty(categoryRegulationOB)) {
            List<CategoryDTO> categoryList = new ArrayList<>();
            categoryRegulationOB.forEach(category -> {
                CategoryDTO categoryDTO = new CategoryDTO();
                BeanUtils.copyProperties(category, categoryDTO);
                categoryList.add(categoryDTO);
            });
            categoryList.sort(Comparator.comparing(CategoryDTO::getRodzajKategorii)
                    .thenComparing(Comparator.comparing(CategoryDTO::getCzyPubliczny))
                    .reversed());
            response.setListaKategorii(categoryList);
        } else {
            response.setListaKategorii(new ArrayList<>());
        }
        return response;
    }

    public CategoryListDTO getCategoryListByName(String term) {
        CategoryListDTO response = new CategoryListDTO();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CategoryRegulationOB> cq = cb.createQuery(CategoryRegulationOB.class);

        Root<CategoryRegulationOB> categoryRegulationOBRoot = cq.from(CategoryRegulationOB.class);
        ParameterExpression<String> parameter = cb.parameter(String.class);
        cq.where(cb.like(cb.lower(categoryRegulationOBRoot.get("rodzajKategorii")), parameter));

        TypedQuery<CategoryRegulationOB> query = entityManager.createQuery(cq);
        query.setParameter(parameter, "%" + term.toLowerCase() + "%");

        List<CategoryRegulationOB> resultList = query.getResultList();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        resultList.forEach(categoryRegulationOB -> {
                    CategoryDTO categoryDTO = new CategoryDTO();
                    BeanUtils.copyProperties(categoryRegulationOB, categoryDTO);
                    categoryDTOList.add(categoryDTO);
                }
        );
        response.setListaKategorii(categoryDTOList);
        return response;
    }
}
