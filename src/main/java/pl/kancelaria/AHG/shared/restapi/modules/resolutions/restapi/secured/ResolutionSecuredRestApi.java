package pl.kancelaria.AHG.shared.restapi.modules.resolutions.restapi.secured;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kancelaria.AHG.modules.resolutions.dto.CreateResotutionDTO;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionDTO;

import javax.ws.rs.*;


@Path(value = ResolutionSecuredRestApiUrl.SCIEZKA_UCHWALY)
@RequestMapping(value = ResolutionSecuredRestApiUrl.SCIEZKA_UCHWALY)
public interface ResolutionSecuredRestApi {

    @POST
    @PostMapping(ResolutionSecuredRestApiUrl.DODAJ_UCHWALE)
    @Path(ResolutionSecuredRestApiUrl.DODAJ_UCHWALE)
    ResponseEntity<HttpStatus> dodajUchale(@RequestBody CreateResotutionDTO resolutionDTO);

    @PUT
    @PutMapping(ResolutionSecuredRestApiUrl.MODYFIKUJ_UCHWALE + "/{id}")
    @Path(ResolutionSecuredRestApiUrl.MODYFIKUJ_UCHWALE)
    ResolutionDTO modyfikujUchwale(@PathVariable(value = "id") long id, @Validated @RequestBody ResolutionDTO request);

    @DELETE
    @DeleteMapping(ResolutionSecuredRestApiUrl.USUN_UCHWALE + "/{id}")
    @Path(ResolutionSecuredRestApiUrl.USUN_UCHWALE)
    ResponseEntity<HttpStatus> usunUchwale(long id);

    @GET
    @GetMapping(ResolutionSecuredRestApiUrl.SZCZEGOLY_UCHWALY + "/{id}")
    @Path(ResolutionSecuredRestApiUrl.SZCZEGOLY_UCHWALY)
    ResolutionDTO szczegolyUchwaly(@PathVariable(value = "id") long id);
}
