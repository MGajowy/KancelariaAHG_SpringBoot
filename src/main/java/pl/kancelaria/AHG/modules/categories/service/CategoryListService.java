package pl.kancelaria.AHG.modules.categories.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.kancelaria.AHG.comon.model.resolutions.categories.CategoriesOB;
import pl.kancelaria.AHG.comon.model.resolutions.categories.repository.CategoriesRepository;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;
import pl.kancelaria.AHG.modules.categories.dto.CategoryListDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal
 * @created 30/07/2020
 */
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
            response.setListaKategorii(kategoria);
        } else {
            response.setListaKategorii(new ArrayList<>());
        }
        return response;
    }

}
