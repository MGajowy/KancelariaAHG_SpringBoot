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
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionDTO;
import pl.kancelaria.AHG.user.services.UserService;

/**
 * @author Michal
 * @created 09/01/2021
 */
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

    public ResolutionDTO modyfikujUchwale(long id, ResolutionDTO request) {
        CategoriesOB categoriesOB = categoriesRepository.getOne(request.getId());
        ResolutionsOB resolutionsOB = resolutionsRepository.getOne(id);
        resolutionsOB.setOpis(request.getOpis());
        resolutionsOB.setTresc(request.getTresc());
        resolutionsOB.setCzyPubliczny(request.getCzyPubliczny());
        resolutionsOB.setKategoria(categoriesOB);
        resolutionsRepository.save(resolutionsOB);
        logger.info("Zmodyfikowano uchwałę " + request.getOpis());
        eventLogService.dodajLog(EventLogConstants.MODYFIKACJA_UCHWALY, null);
        return request;
    }

}
