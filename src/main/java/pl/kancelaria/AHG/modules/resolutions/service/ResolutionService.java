package pl.kancelaria.AHG.modules.resolutions.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.kancelaria.AHG.comon.model.resolutions.categories.CategoriesOB;
import pl.kancelaria.AHG.comon.model.resolutions.categories.repository.CategoriesRepository;
import pl.kancelaria.AHG.comon.model.resolutions.resolutions.ResolutionsOB;
import pl.kancelaria.AHG.comon.model.resolutions.resolutions.repository.ResolutionsRepository;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionDTO;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionListDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal
 * @created 09/01/2021
 */
@Service
public class ResolutionService {

    private final ResolutionsRepository resolutionsRepository;
    private final CategoriesRepository categoriesRepository;


    public ResolutionService(ResolutionsRepository resolutionsRepository, CategoriesRepository categoriesRepository) {
        this.resolutionsRepository = resolutionsRepository;
        this.categoriesRepository = categoriesRepository;
    }

    public ResolutionListDTO pobierzListeUchwal() {
        ResolutionListDTO resolutionListDTO = new ResolutionListDTO();
        List<ResolutionsOB> resolutionsOBS = this.resolutionsRepository.findAll();
        if (!CollectionUtils.isEmpty(resolutionsOBS)) {
            List<ResolutionDTO> listaUchwal = new ArrayList<>();
            resolutionsOBS.forEach(e -> {
                ResolutionDTO daneDTO = new ResolutionDTO();
                BeanUtils.copyProperties(e, daneDTO);
                //todo dokończyc obsluge kategorii do wyswietlania  listy uchwał.
//                CategoriesOB categoriesOB = categoriesRepository.getOne();
//                e.setFk_kategorii(categoriesOB);
                listaUchwal.add(daneDTO);
            });
            resolutionListDTO.setListaUchwal(listaUchwal);
        } else {
            resolutionListDTO.setListaUchwal(new ArrayList<>());
        }

        return resolutionListDTO;
    }
}
