package pl.kancelaria.AHG.modules.resolutions.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class CreateResolutionService {

    private final ResolutionsRepository resolutionsRepository;
    private final CategoriesRepository categoriesRepository;
    private final EventLogService eventLogService;
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Transactional
    public ResponseEntity<HttpStatus> addNewResolution(CreateResotutionDTO resolutionDTO, HttpServletRequest request) {
        CategoriesOB categoriesOB = categoriesRepository.getOne(resolutionDTO.getKategoria());
        if (categoriesOB != null && validationRequest(resolutionDTO)) {
            ResolutionsOB resolutionsOB =  new ResolutionsOB();
            resolutionsOB.setOpis(resolutionDTO.getOpis());
            resolutionsOB.setTresc(resolutionDTO.getTresc());
            resolutionsOB.setKategoria(categoriesOB);
            resolutionsOB.setCzyPubliczny(resolutionDTO.getCzyPubliczny());
            this.resolutionsRepository.save(resolutionsOB);
            logger.info("Uchwała " + resolutionsOB.getOpis() + " została dodana do listy.");
            eventLogService.createLog(EventLogConstants.DODANIE_NOWEJ_UCHWALY, request.getRemoteUser() );
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private boolean validationRequest(CreateResotutionDTO resolutionDTO) {
        return resolutionDTO.getCzyPubliczny() != null && resolutionDTO.getOpis() != null && resolutionDTO.getTresc() != null;
    }

}
