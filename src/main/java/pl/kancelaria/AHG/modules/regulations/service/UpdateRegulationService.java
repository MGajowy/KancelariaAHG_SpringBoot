package pl.kancelaria.AHG.modules.regulations.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.administration.services.EventLogService;
import pl.kancelaria.AHG.common.entityModel.administration.eventLog.EventLogConstants;
import pl.kancelaria.AHG.common.entityModel.regulations.category.CategoryRegulationOB;
import pl.kancelaria.AHG.common.entityModel.regulations.category.repository.CategoryRegulationRepository;
import pl.kancelaria.AHG.common.entityModel.regulations.regulation.RegulationOB;
import pl.kancelaria.AHG.common.entityModel.regulations.regulation.repository.RegulationRepository;
import pl.kancelaria.AHG.modules.regulations.dto.UpdateRegulationDTO;
import pl.kancelaria.AHG.user.services.UserService;

import javax.transaction.Transactional;

@Service
public class UpdateRegulationService {

    private final RegulationRepository regulationRepository;
    private final CategoryRegulationRepository categoryRegulationRepository;
    private final EventLogService eventLogService;
    Logger logger = LoggerFactory.getLogger(UserService.class);

    public UpdateRegulationService(RegulationRepository regulationRepository, CategoryRegulationRepository categoryRegulationRepository, EventLogService eventLogService) {
        this.regulationRepository = regulationRepository;
        this.categoryRegulationRepository = categoryRegulationRepository;
        this.eventLogService = eventLogService;
    }

    @Transactional
    public ResponseEntity<HttpStatus> modifyRegulation(long id, UpdateRegulationDTO request) {
        if (validation(request)) {
            CategoryRegulationOB categoryRegulationOB = categoryRegulationRepository.getOne(request.getCategoryId());
            RegulationOB regulationOB = regulationRepository.getOne(id);
            regulationOB.setRegulationName(request.getRegulationName());
            regulationOB.setContents(request.getContents());
            regulationOB.setIsPublic(request.getIsPublic());
            regulationOB.setCategory(categoryRegulationOB);
            regulationRepository.save(regulationOB);
            logger.info("Zmodyfikowano rozporządzenie " + regulationOB.getRegulationName());
            eventLogService.createLog(EventLogConstants.MODYFIKACJA_ROZPORZADZENIA, "");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            logger.warn("Błąd podczas modyfikacji rozporządzenia ");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    private boolean validation(UpdateRegulationDTO request) {
        return request.getCategoryId() != null && request.getRegulationName().isEmpty();
    }
}