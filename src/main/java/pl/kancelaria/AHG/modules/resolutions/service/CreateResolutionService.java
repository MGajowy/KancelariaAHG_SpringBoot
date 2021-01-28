package pl.kancelaria.AHG.modules.resolutions.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.administration.services.EventLogService;
import pl.kancelaria.AHG.comon.model.administration.eventLog.EventLogConstants;
import pl.kancelaria.AHG.comon.model.resolutions.categories.CategoriesOB;
import pl.kancelaria.AHG.comon.model.resolutions.categories.repository.CategoriesRepository;
import pl.kancelaria.AHG.comon.model.resolutions.resolutions.ResolutionsOB;
import pl.kancelaria.AHG.comon.model.resolutions.resolutions.repository.ResolutionsRepository;
import pl.kancelaria.AHG.modules.resolutions.dto.CreateResotutionDTO;
import pl.kancelaria.AHG.user.services.UserService;

import java.util.List;
import java.util.Optional;

/**
 * @author Michal
 * @created 09/01/2021
 */
@Service
public class CreateResolutionService {

    private final ResolutionsRepository resolutionsRepository;
    private final CategoriesRepository categoriesRepository;
    private final EventLogService eventLogService;
    Logger logger = LoggerFactory.getLogger(UserService.class);

    public CreateResolutionService(ResolutionsRepository resolutionsRepository, CategoriesRepository categoriesRepository, EventLogService eventLogService) {
        this.resolutionsRepository = resolutionsRepository;
        this.categoriesRepository = categoriesRepository;
        this.eventLogService = eventLogService;
    }

    public void dodajUchwale(CreateResotutionDTO resolutionDTO) {
        CategoriesOB categoriesOB = categoriesRepository.getOne(resolutionDTO.getKategoria());
        ResolutionsOB resolutionsOB =  new ResolutionsOB();
        resolutionsOB.setOpis(resolutionDTO.getOpis());
        resolutionsOB.setTresc(resolutionDTO.getTresc());
        resolutionsOB.setKategoria(categoriesOB);
        resolutionsOB.setCzyPubliczny(resolutionDTO.getCzyPubliczny());
        this.resolutionsRepository.save(resolutionsOB);
        logger.info("Uchwała " + resolutionsOB.getOpis() + "zostałą dodana do listy.");

        eventLogService.dodajLog(EventLogConstants.DODANIE_NOWEJ_UCHWALY, null);
    }

}
