package pl.kancelaria.AHG.modules.regulations.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.administration.services.EventLogService;
import pl.kancelaria.AHG.common.entityModel.administration.eventLog.EventLogConstants;
import pl.kancelaria.AHG.common.entityModel.regulations.regulation.RegulationOB;
import pl.kancelaria.AHG.common.entityModel.regulations.regulation.repository.RegulationRepository;
import pl.kancelaria.AHG.user.services.UserService;

@Service
public class DeleteRegulationService {

    private final RegulationRepository regulationRepository;
    private final EventLogService eventLogService;
    Logger logger = LoggerFactory.getLogger(UserService.class);

    public DeleteRegulationService(RegulationRepository regulationRepository, EventLogService eventLogService) {
        this.regulationRepository = regulationRepository;
        this.eventLogService = eventLogService;
    }

    public ResponseEntity<HttpStatus> deleteRegulation(long id) {
        try {
            RegulationOB regulationOB = regulationRepository.getOne(id);
            regulationRepository.deleteById(id);
            logger.info("Rozporządzenie {} zostało usunięte", regulationOB.getNazwa());
            eventLogService.createLog(EventLogConstants.USUNIECIE_ROZPORZADZENIA, "");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Nieudana próba usunięcia rozporządzenia", ex);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
