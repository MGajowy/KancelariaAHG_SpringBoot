package pl.kancelaria.AHG.modules.resolutions.restapi.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.modules.resolutions.dto.CreateResotutionDTO;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionDTO;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionRequestDTO;
import pl.kancelaria.AHG.modules.resolutions.service.CreateResolutionService;
import pl.kancelaria.AHG.modules.resolutions.service.DeleteResolutionService;
import pl.kancelaria.AHG.modules.resolutions.service.DetailsResolutionService;
import pl.kancelaria.AHG.modules.resolutions.service.UpdateResolutionService;

import javax.servlet.http.HttpServletRequest;


@RestController
public class ResolutionSecuredRestApi implements pl.kancelaria.AHG.shared.restapi.modules.resolutions.restapi.secured.ResolutionSecuredRestApi {

    private final CreateResolutionService createResolutionService;
    private final DeleteResolutionService deleteResolutionService;
    private final UpdateResolutionService updateResolutionService;
    private final DetailsResolutionService detailsResolutionService;

    @Autowired
    public ResolutionSecuredRestApi(CreateResolutionService createResolutionService, DeleteResolutionService deleteResolutionService, UpdateResolutionService updateResolutionService, DetailsResolutionService detailsResolutionService) {
        this.createResolutionService = createResolutionService;
        this.deleteResolutionService = deleteResolutionService;
        this.updateResolutionService = updateResolutionService;
        this.detailsResolutionService = detailsResolutionService;
    }

    @Override
    public ResponseEntity<HttpStatus> addNewResolution(CreateResotutionDTO resolutionDTO, HttpServletRequest request) {
        return createResolutionService.addNewResolution(resolutionDTO, request);
    }

    @Override
    public ResolutionRequestDTO modifyResolution(long id, ResolutionRequestDTO request) {
        return this.updateResolutionService.modifyResolution(id, request);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteResolution(long id) {
        return deleteResolutionService.deleteResolution(id);
    }

    @Override
    public ResolutionDTO detailsResolution(long id) {
        return this.detailsResolutionService.detailsResolution(id);
    }
}
