package pl.kancelaria.AHG.modules.regulations.restApi.secured;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.modules.regulations.dto.CreateRegulationDTO;
import pl.kancelaria.AHG.modules.regulations.dto.RegulationDTO;
import pl.kancelaria.AHG.modules.regulations.dto.UpdateRegulationDTO;
import pl.kancelaria.AHG.modules.regulations.service.CreateRegulationService;
import pl.kancelaria.AHG.modules.regulations.service.DeleteRegulationService;
import pl.kancelaria.AHG.modules.regulations.service.RegulationService;
import pl.kancelaria.AHG.modules.regulations.service.UpdateRegulationService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RegulationSecuredRestApi implements pl.kancelaria.AHG.shared.restapi.modules.regulations.restApi.secured.RegulationSecuredRestApi {

    private final CreateRegulationService createRegulationService;
    private final DeleteRegulationService deleteRegulationService;
    private final UpdateRegulationService updateRegulationService;
    private final RegulationService regulationService;

    public RegulationSecuredRestApi(CreateRegulationService createRegulationService, DeleteRegulationService deleteRegulationService, UpdateRegulationService updateRegulationService, RegulationService regulationService) {
        this.createRegulationService = createRegulationService;
        this.deleteRegulationService = deleteRegulationService;
        this.updateRegulationService = updateRegulationService;
        this.regulationService = regulationService;
    }

    @Override
    public ResponseEntity<HttpStatus> addNewRegulation(CreateRegulationDTO regulationDTO, HttpServletRequest request) {
        return createRegulationService.addNewRegulation(regulationDTO, request);
    }

    @Override
    public ResponseEntity<HttpStatus> modifyRegulation(long id, UpdateRegulationDTO request) {
        return updateRegulationService.modifyRegulation(id, request);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteRegulation(long id) {
        return deleteRegulationService.deleteRegulation(id);
    }

    //todo obsłużyć na froncie !
    @Override
    public RegulationDTO detailsRegulation(long id) {
        return regulationService.detailsRegulation(id);
    }
}
