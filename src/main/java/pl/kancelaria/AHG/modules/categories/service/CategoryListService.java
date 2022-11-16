package pl.kancelaria.AHG.modules.categories.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.CategoriesOB;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.repository.CategoriesRepository;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTOrequest;
import pl.kancelaria.AHG.modules.categories.dto.CategoryListDTO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryListService {

    public final CategoriesRepository categoriesRepository;
    public final EntityManager entityManager;
    Logger logger = LoggerFactory.getLogger(CategoryListService.class);

    public CategoryListService(CategoriesRepository categoriesRepository, EntityManager entityManager) {
        this.categoriesRepository = categoriesRepository;
        this.entityManager = entityManager;
    }

    public CategoryListDTO getCategoryList() {
        CategoryListDTO response = new CategoryListDTO();
        List<CategoriesOB> categoriesOBList = this.categoriesRepository.findAll();
        if (!CollectionUtils.isEmpty(categoriesOBList)) {
            List<CategoryDTO> categoryList = new ArrayList<>();
            categoriesOBList.forEach(e -> {
                CategoryDTO daneDTO = new CategoryDTO();
                BeanUtils.copyProperties(e, daneDTO);
                categoryList.add(daneDTO);
            });
            categoryList.sort(Comparator.comparing(CategoryDTO::getRodzajKategorii).reversed());
            response.setListaKategorii(categoryList);
        } else {
            response.setListaKategorii(new ArrayList<>());
        }
        return response;
    }

    public CategoryDTOrequest getCategoriesById(long id) {
        CategoriesOB categoriesOB = categoriesRepository.getOne(id);
        CategoryDTOrequest categoryDTOrequest = new CategoryDTOrequest();
        BeanUtils.copyProperties(categoriesOB, categoryDTOrequest);
        return categoryDTOrequest;
    }

    public CategoryListDTO searchCategories(String term) {
        CategoryListDTO response = new CategoryListDTO();

        List<CategoriesOB> listOB = provideListOfCriteria(term);
        if (!CollectionUtils.isEmpty(listOB)) {
            List<CategoryDTO> listCategory = new ArrayList<>();
            listOB.forEach(categoriesOB -> {
                CategoryDTO categoryDTO = new CategoryDTO();
                BeanUtils.copyProperties(categoriesOB, categoryDTO);
                listCategory.add(categoryDTO);
            });
            response.setListaKategorii(listCategory);
        } else {
            response.setListaKategorii(new ArrayList<>());
        }
        return response;
    }

    private List<CategoriesOB> provideListOfCriteria(String term) {
        TypedQuery<CategoriesOB> query = entityManager.createQuery(prepareAnInquiry(term), CategoriesOB.class);
        if (!term.isEmpty())
            query.setParameter("term", "%" + term.toLowerCase() + "%");
        return query.getResultList();
    }

    private String prepareAnInquiry(String term) {
        StringBuilder query = new StringBuilder("SELECT c FROM CategoriesOB c");
        if (!term.isEmpty()) {
            query.append(" WHERE LOWER(c.rodzajKategorii) like :term");
        }
        query.append(" ORDER BY c.rodzajKategorii DESC");
        return query.toString();
    }

    public CategoryListDTO searchCategoriesByStatus(Boolean status) {
        CategoryListDTO categoryListDTO = new CategoryListDTO();
        List<CategoriesOB> listCategoryOB = getListCategoriesByStatus(status);
        List<CategoryDTO> collect = listCategoryOB.stream().map(CategoriesOB::categoryListByStatus).collect(Collectors.toList());
        categoryListDTO.setListaKategorii(collect);
        return categoryListDTO;
    }

    private List<CategoriesOB> getListCategoriesByStatus(Boolean status) {
        TypedQuery<CategoriesOB> query = entityManager.createQuery(prepareInquiryForStatus(status), CategoriesOB.class);
        query.setParameter("status", status);
        return query.getResultList();
    }

    private String prepareInquiryForStatus(Boolean status) {
        StringBuilder query = new StringBuilder("SELECT c FROM CategoriesOB c");
        query.append(" WHERE c.czyPubliczny = :status");
        query.append(" ORDER BY c.rodzajKategorii DESC");
        return query.toString();
    }

    //todo metoda nieużywana - niepodłączona
    public List<String> getCategoryListByName2(String nazwaKaregorii) {
        List<CategoriesOB> categoriesOB = categoriesRepository.findCategoriesOBsByRodzajKategorii(nazwaKaregorii);
        return categoriesOB.stream()
                .map(CategoriesOB::getRodzajKategorii)
                .collect(Collectors.toList());
    }

    //todo metoda nieużywana
    public List<String> getCategoryListByName(String nazwaKategorii) {
        List<CategoriesOB> categoriesOB = categoriesRepository.findCategoriesByRodzajKategoriiImpl(nazwaKategorii);
        return categoriesOB.stream()
                .map(CategoriesOB::getRodzajKategorii)
                .filter(rodzajKategorii -> rodzajKategorii.equals(nazwaKategorii))
                .collect(Collectors.toList());
    }
    }
