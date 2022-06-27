package pl.kancelaria.AHG.modules.resolutions.restapi.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.modules.resolutions.dto.CreateResotutionDTO;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionDTO;
import pl.kancelaria.AHG.modules.resolutions.service.CreateResolutionService;
import pl.kancelaria.AHG.modules.resolutions.service.DeleteResolutionService;
import pl.kancelaria.AHG.modules.resolutions.service.DetailsResolutionService;
import pl.kancelaria.AHG.modules.resolutions.service.UpdateResolutionService;


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
    public ResponseEntity<HttpStatus> dodajUchale(CreateResotutionDTO resolutionDTO) {
        createResolutionService.dodajUchwale(resolutionDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResolutionDTO modyfikujUchwale(long id, ResolutionDTO request) {
        return this.updateResolutionService.modyfikujUchwale(id, request);
    }

    @Override
    public ResponseEntity<HttpStatus> usunUchwale(long id) {
        deleteResolutionService.usunUchwale(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //todo dokonczyc serwis szczegolow
    @Override
    public ResolutionDTO szczegolyUchwaly(long id) {
        return this.detailsResolutionService.szczegolyUchwaly(id);
    }
}
