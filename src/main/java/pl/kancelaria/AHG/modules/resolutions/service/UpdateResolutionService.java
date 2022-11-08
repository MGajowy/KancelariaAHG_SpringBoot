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
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionDTO;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionRequestDTO;
import pl.kancelaria.AHG.user.services.UserService;


@Service
public class UpdateResolutionService {

    private final ResolutionsRepository resolutionsRepository;
    private final CategoriesRepository categoriesRepository;
    private final EventLogService eventLogService;
    Logger logger = LoggerFactory.getLogger(UserService.class);

    public UpdateResolutionService(ResolutionsRepository resolutionsRepository, CategoriesRepository categoriesRepository, EventLogService eventLogService) {
        this.resolutionsRepository = resolutionsRepository;
        this.categoriesRepository = categoriesRepository;
        this.eventLogService = eventLogService;
    }

    public ResolutionRequestDTO modifyResolution(long id, ResolutionRequestDTO request) {
        CategoriesOB categoriesOB = categoriesRepository.getOne(request.getIdKategorii());
        ResolutionsOB resolutionsOB = resolutionsRepository.getOne(id);
        resolutionsOB.setOpis(request.getOpis());
        resolutionsOB.setTresc(request.getTresc());
        resolutionsOB.setCzyPubliczny(request.getCzyPubliczny());
        resolutionsOB.setKategoria(categoriesOB);
        resolutionsRepository.save(resolutionsOB);
        logger.info("Zmodyfikowano uchwałę " + request.getOpis());
        eventLogService.createLog(EventLogConstants.MODYFIKACJA_UCHWALY, "");
        return request;
    }

}
