package pl.kancelaria.AHG.modules.categories.service;

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

    public CategoryListService(CategoriesRepository categoriesRepository, EntityManager entityManager) {
        this.categoriesRepository = categoriesRepository;
        this.entityManager = entityManager;
    }

    public CategoryListDTO pobierzListeKategorii() {
        CategoryListDTO response = new CategoryListDTO();
        List<CategoriesOB> categoriesOBList = this.categoriesRepository.findAll();
        if (!CollectionUtils.isEmpty(categoriesOBList)) {
            List<CategoryDTO> kategoria = new ArrayList<>();
            categoriesOBList.forEach(e -> {
                CategoryDTO daneDTO = new CategoryDTO();
                BeanUtils.copyProperties(e, daneDTO);
                kategoria.add(daneDTO);
            });
            kategoria.sort(Comparator.comparing(CategoryDTO::getRodzajKategorii).reversed());
            response.setListaKategorii(kategoria);
        } else {
            response.setListaKategorii(new ArrayList<>());
        }
        return response;
    }

    public CategoryDTOrequest pobierzKategoriePoId(long id) {
        CategoriesOB categoriesOB = categoriesRepository.getOne(id);
        CategoryDTOrequest categoryDTOrequest = new CategoryDTOrequest();
        BeanUtils.copyProperties(categoriesOB, categoryDTOrequest);
        return categoryDTOrequest;
    }

    public List<String> pobierzListeKategoriiPoNazwie(String nazwaKategorii) {
        List<CategoriesOB> categoriesOB = categoriesRepository.findCategoriesByRodzajKategoriiImpl(nazwaKategorii);
        return categoriesOB.stream()
                .map(CategoriesOB::getRodzajKategorii)
                .filter(rodzajKategorii -> rodzajKategorii.equals(nazwaKategorii)).collect(Collectors.toList());
    }

    public CategoryListDTO wyszukajKategorie(String term) {
        CategoryListDTO respose = new CategoryListDTO();
        List<CategoriesOB> listOB = podajListeWedlugKryteriow(term);
        if (!CollectionUtils.isEmpty(listOB)) {
            List<CategoryDTO> listCategory = new ArrayList<>();
            listOB.forEach(categoriesOB -> {
                CategoryDTO categoryDTO = new CategoryDTO();
                BeanUtils.copyProperties(categoriesOB, categoryDTO);
                listCategory.add(categoryDTO);
            });
            respose.setListaKategorii(listCategory);
        } else {
            respose.setListaKategorii(new ArrayList<>());
        }
        return respose;
    }

    private List<CategoriesOB> podajListeWedlugKryteriow(String term) {
        TypedQuery<CategoriesOB> query = entityManager.createQuery(przygotujZapytanie(term), CategoriesOB.class);
        if (!term.isEmpty())
            query.setParameter("term", "%" + term.toLowerCase() + "%");
        return query.getResultList();
    }

    private String przygotujZapytanie(String term) {
        StringBuilder query = new StringBuilder("SELECT c FROM CategoriesOB c");
        if (!term.isEmpty()) {
            query.append(" WHERE LOWER(c.rodzajKategorii) like :term");
        }
        query.append(" ORDER BY c.rodzajKategorii DESC");
        return query.toString();
    }

    public CategoryListDTO wyszukajKategoriePoStatusie(Boolean status) {
        CategoryListDTO categoryListDTO = new CategoryListDTO();
        List<CategoriesOB> listCategoryOB = podajListeWedlugStatusu(status);
        List<CategoryDTO> collect = listCategoryOB.stream().map(CategoriesOB::listKategoriiPoStatus).collect(Collectors.toList());
        categoryListDTO.setListaKategorii(collect);
        return categoryListDTO;
    }

    private List<CategoriesOB> podajListeWedlugStatusu(Boolean status) {
        TypedQuery<CategoriesOB> query = entityManager.createQuery(przygotujZapytanieStatusu(status), CategoriesOB.class);
        query.setParameter("status", status);
        return query.getResultList();
    }

    private String przygotujZapytanieStatusu(Boolean status) {
        StringBuilder query = new StringBuilder("SELECT c FROM CategoriesOB c");
            query.append(" WHERE c.czyPubliczny = :status");
        query.append(" ORDER BY c.rodzajKategorii DESC");
        return query.toString();
    }
}
