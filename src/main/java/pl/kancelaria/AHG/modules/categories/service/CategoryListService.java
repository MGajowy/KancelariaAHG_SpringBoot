package pl.kancelaria.AHG.modules.categories.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.kancelaria.AHG.comon.model.resolutions.categories.CategoriesOB;
import pl.kancelaria.AHG.comon.model.resolutions.categories.repository.CategoriesRepository;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTOrequest;
import pl.kancelaria.AHG.modules.categories.dto.CategoryListDTO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryListService {

    public final CategoriesRepository categoriesRepository;

    public CategoryListService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
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
}
