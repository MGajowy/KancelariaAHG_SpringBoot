package pl.kancelaria.AHG.modules.resolutions.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.administration.services.EventLogService;
import pl.kancelaria.AHG.comon.model.administration.eventLog.EventLogConstants;
import pl.kancelaria.AHG.comon.model.resolutions.resolutions.ResolutionsOB;
import pl.kancelaria.AHG.comon.model.resolutions.resolutions.repository.ResolutionsRepository;
import pl.kancelaria.AHG.user.services.UserService;

/**
 * @author Michal
 * @created 09/01/2021
 */
@Service
public class DeleteResolutionService {

    private final ResolutionsRepository resolutionsRepository;
    private final EventLogService eventLogService;
    Logger logger = LoggerFactory.getLogger(UserService.class);

    public DeleteResolutionService(ResolutionsRepository resolutionsRepository, EventLogService eventLogService) {
        this.resolutionsRepository = resolutionsRepository;
        this.eventLogService = eventLogService;
    }

    public Boolean usunUchwale(long id) {
        try {
            ResolutionsOB resolutionsOB = resolutionsRepository.getOne(id);
            resolutionsRepository.deleteById(id);
            logger.info("Uchwala " + resolutionsOB.getOpis() + "została usunięta.");
            eventLogService.dodajLog(EventLogConstants.USUNIECIE_UCHWALY,null);
            return true;
        } catch (Exception ex) {
            logger.error("Niepoprawnie usunięto uchwałę", ex);
            return false;
        }
    }

}


