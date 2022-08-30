package pl.kancelaria.AHG.modules.resolutions.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.administration.services.EventLogService;
import pl.kancelaria.AHG.common.entityModel.administration.eventLog.EventLogConstants;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.CategoriesOB;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.repository.CategoriesRepository;
import pl.kancelaria.AHG.common.entityModel.resolutions.resolutions.ResolutionsOB;
import pl.kancelaria.AHG.common.entityModel.resolutions.resolutions.repository.ResolutionsRepository;
import pl.kancelaria.AHG.modules.resolutions.dto.CreateResotutionDTO;
import pl.kancelaria.AHG.user.services.UserService;

import javax.servlet.http.HttpServletRequest;


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

    public void dodajUchwale(CreateResotutionDTO resolutionDTO, HttpServletRequest request) {
        CategoriesOB categoriesOB = categoriesRepository.getOne(resolutionDTO.getKategoria());
        ResolutionsOB resolutionsOB =  new ResolutionsOB();
        resolutionsOB.setOpis(resolutionDTO.getOpis());
        resolutionsOB.setTresc(resolutionDTO.getTresc());
        resolutionsOB.setKategoria(categoriesOB);
        resolutionsOB.setCzyPubliczny(resolutionDTO.getCzyPubliczny());
        this.resolutionsRepository.save(resolutionsOB);
        logger.info("Uchwała " + resolutionsOB.getOpis() + "została dodana do listy.");
        eventLogService.dodajLog(EventLogConstants.DODANIE_NOWEJ_UCHWALY, request.getRemoteUser() );
    }

}
