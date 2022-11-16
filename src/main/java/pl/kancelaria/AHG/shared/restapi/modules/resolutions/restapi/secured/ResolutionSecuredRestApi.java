package pl.kancelaria.AHG.shared.restapi.modules.resolutions.restapi.secured;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kancelaria.AHG.modules.resolutions.dto.CreateResotutionDTO;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionDTO;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionRequestDTO;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;


@Path(value = ResolutionSecuredRestApiUrl.SCIEZKA_UCHWALY)
@RequestMapping(value = ResolutionSecuredRestApiUrl.SCIEZKA_UCHWALY)
public interface ResolutionSecuredRestApi {

    @POST
    @PostMapping(ResolutionSecuredRestApiUrl.DODAJ_UCHWALE)
    @Path(ResolutionSecuredRestApiUrl.DODAJ_UCHWALE)
    ResponseEntity<HttpStatus> addNewResolution(@RequestBody CreateResotutionDTO resolutionDTO, HttpServletRequest request);

    @PUT
    @PutMapping(ResolutionSecuredRestApiUrl.MODYFIKUJ_UCHWALE + "/{id}")
    @Path(ResolutionSecuredRestApiUrl.MODYFIKUJ_UCHWALE)
    ResponseEntity<HttpStatus> modifyResolution(@PathVariable(value = "id") long id, @RequestBody ResolutionRequestDTO request);

    @DELETE
    @DeleteMapping(ResolutionSecuredRestApiUrl.USUN_UCHWALE + "/{id}")
    @Path(ResolutionSecuredRestApiUrl.USUN_UCHWALE)
    ResponseEntity<HttpStatus> deleteResolution(@PathVariable(value = "id") long id);

    @GET
    @GetMapping(ResolutionSecuredRestApiUrl.SZCZEGOLY_UCHWALY + "/{id}")
    @Path(ResolutionSecuredRestApiUrl.SZCZEGOLY_UCHWALY)
    ResolutionDTO detailsResolution(@PathVariable(value = "id") long id);
}
