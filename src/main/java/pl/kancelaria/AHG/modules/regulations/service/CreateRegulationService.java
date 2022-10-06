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
import pl.kancelaria.AHG.modules.regulations.dto.CreateRegulationDTO;
import pl.kancelaria.AHG.user.services.UserService;

import javax.servlet.http.HttpServletRequest;

@Service
public class CreateRegulationService {

    private final RegulationRepository regulationRepository;
    private final CategoryRegulationRepository categoryRegulationRepository;
    private final EventLogService eventLogService;
    Logger logger = LoggerFactory.getLogger(UserService.class);

    public CreateRegulationService(RegulationRepository regulationRepository,
                                   CategoryRegulationRepository categoryRegulationRepository, EventLogService eventLogService) {
        this.regulationRepository = regulationRepository;
        this.categoryRegulationRepository = categoryRegulationRepository;
        this.eventLogService = eventLogService;
    }

    public ResponseEntity<HttpStatus> dodajNoweRozporzadzenie(CreateRegulationDTO regulationDTO, HttpServletRequest request) {
        CategoryRegulationOB categoryRegulationOB = categoryRegulationRepository.getOne(regulationDTO.getKategoria());
        if (categoryRegulationOB != null) {
            RegulationOB regulationOB = new RegulationOB();
            regulationOB.setKategoria(categoryRegulationOB);
            regulationOB.setTresc(regulationDTO.getTresc());
            regulationOB.setCzyPubliczny(regulationDTO.getCzyPubliczny());
            regulationOB.setNazwa(regulationDTO.getNazwa());
            this.regulationRepository.save(regulationOB);
            logger.info("Rozporzadzenie " + regulationDTO.getNazwa() + " zostało dodane do listy.");
            eventLogService.dodajLog(EventLogConstants.DODANIE_NOWEGO_ROZPORZADZENIA, request.getRemoteUser());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
