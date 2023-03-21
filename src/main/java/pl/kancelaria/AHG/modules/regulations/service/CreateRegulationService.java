package pl.kancelaria.AHG.modules.regulations.service;

import lombok.RequiredArgsConstructor;
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
import pl.kancelaria.AHG.modules.regulations.dto.CreateRegulationDTO;
import pl.kancelaria.AHG.user.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class CreateRegulationService {

    private final RegulationRepository regulationRepository;
    private final CategoryRegulationRepository categoryRegulationRepository;
    private final EventLogService eventLogService;
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Transactional
    public ResponseEntity<HttpStatus> addNewRegulation(CreateRegulationDTO regulationDTO, HttpServletRequest request) {
        CategoryRegulationOB categoryRegulationOB = categoryRegulationRepository.getOne(regulationDTO.getCategory());
        Date date = new Date();
        date.getTime();
        if (categoryRegulationOB != null && validationRequest(regulationDTO)) {
            RegulationOB regulationOB = new RegulationOB();
            regulationOB.setCategory(categoryRegulationOB);
            regulationOB.setContents(regulationDTO.getContents());
            regulationOB.setIsPublic(regulationDTO.getIsPublic());
            regulationOB.setRegulationName(regulationDTO.getRegulationName());
            regulationOB.setDateAdded(date);
            this.regulationRepository.save(regulationOB);
            logger.info("Rozporzadzenie " + regulationDTO.getRegulationName() + " zostało dodane do listy.");
            eventLogService.createLog(EventLogConstants.DODANIE_NOWEGO_ROZPORZADZENIA, request.getRemoteUser());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private boolean validationRequest(CreateRegulationDTO regulationDTO) {
        return regulationDTO.getRegulationName() != null && regulationDTO.getIsPublic() != null && regulationDTO.getContents() != null;
    }
}
