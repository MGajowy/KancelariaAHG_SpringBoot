package pl.kancelaria.AHG.modules.resolutions.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.CategoriesOB;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.repository.CategoriesRepository;
import pl.kancelaria.AHG.common.entityModel.resolutions.resolutions.ResolutionsOB;
import pl.kancelaria.AHG.common.entityModel.resolutions.resolutions.repository.ResolutionsRepository;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionDTO;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionListDTO;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionListOfCategoryDTO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ResolutionService {

    private final ResolutionsRepository resolutionsRepository;
    private final CategoriesRepository categoriesRepository;
    private final EntityManager entityManager;


    public ResolutionService(ResolutionsRepository resolutionsRepository, CategoriesRepository categoriesRepository, EntityManager entityManager) {
        this.resolutionsRepository = resolutionsRepository;
        this.categoriesRepository = categoriesRepository;
        this.entityManager = entityManager;
    }

    public ResolutionListDTO pobierzListeUchwal() {
        ResolutionListDTO resolutionListDTO = new ResolutionListDTO();
        List<ResolutionsOB> resolutionsOBS = this.resolutionsRepository.findAll();
        if (!CollectionUtils.isEmpty(resolutionsOBS)) {
            List<ResolutionDTO> listaUchwal = new ArrayList<>();
            resolutionsOBS.forEach(e -> {
                ResolutionDTO daneDTO = new ResolutionDTO();
                BeanUtils.copyProperties(e, daneDTO);
                CategoriesOB categoriesOB = this.categoriesRepository.getOne(e.getKategoria().getId());
                daneDTO.setNazwaKategorii(categoriesOB.getRodzajKategorii());
                listaUchwal.add(daneDTO);
            });
            resolutionListDTO.setListaUchwal(listaUchwal);
        } else {
            resolutionListDTO.setListaUchwal(new ArrayList<>());
        }
        return resolutionListDTO;
    }

    // todo zrobic walidacje !!!!
    public ResolutionListOfCategoryDTO pobierzListeUchwalPoKategorii(Long idKategorii) {
        List<ResolutionsOB> resolutionsOBS = podajListeWedlugKategorii(idKategorii);
        CategoriesOB categoriesOB = categoriesRepository.getOne(idKategorii);
        return ResolutionListOfCategoryDTO.builder()
                .nazwaKategorii(categoriesOB.getRodzajKategorii())
                .listaUchwal(resolutionsOBS.stream().map(ResolutionsOB::getResolutionDTO).collect(Collectors.toList()))
                .build();
    }

    private List<ResolutionsOB> podajListeWedlugKategorii(Long idKategorii) {
        TypedQuery<ResolutionsOB> query = entityManager.createQuery(przygotujZapytanieListyUchwal(idKategorii), ResolutionsOB.class);
        query.setParameter("idKategoria", idKategorii);
        return query.getResultList();
    }

    private String przygotujZapytanieListyUchwal(Long idKategorii) {
        StringBuilder query = new StringBuilder("SELECT r FROM ResolutionsOB r");
        query.append(" WHERE r.kategoria.id = :idKategoria");
        query.append(" ORDER BY r.opis DESC");
        return query.toString();
    }
}
