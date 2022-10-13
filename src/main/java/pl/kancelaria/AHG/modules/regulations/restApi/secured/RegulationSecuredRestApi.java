package pl.kancelaria.AHG.modules.regulations.restApi.secured;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.modules.regulations.dto.CreateRegulationDTO;
import pl.kancelaria.AHG.modules.regulations.service.CreateRegulationService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RegulationSecuredRestApi implements pl.kancelaria.AHG.shared.restapi.modules.regulations.restApi.secured.RegulationSecuredRestApi {

    private final CreateRegulationService createRegulationService;

    public RegulationSecuredRestApi(CreateRegulationService createRegulationService) {
        this.createRegulationService = createRegulationService;
    }

    @Override
    public ResponseEntity<HttpStatus> dodajRozporzadzenie(CreateRegulationDTO regulationDTO, HttpServletRequest request) {
        return createRegulationService.addNewRegulation(regulationDTO, request);
    }
}
