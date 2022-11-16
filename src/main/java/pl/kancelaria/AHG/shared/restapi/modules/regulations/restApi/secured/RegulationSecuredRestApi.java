package pl.kancelaria.AHG.shared.restapi.modules.regulations.restApi.secured;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kancelaria.AHG.modules.regulations.dto.CreateRegulationDTO;
import pl.kancelaria.AHG.modules.regulations.dto.RegulationDTO;
import pl.kancelaria.AHG.modules.regulations.dto.UpdateRegulationDTO;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;

@Path(value = RegulationSecuredRestApiUrl.SCIEZKA_ROZPORZADZENIA)
@RequestMapping(value = RegulationSecuredRestApiUrl.SCIEZKA_ROZPORZADZENIA)
public interface RegulationSecuredRestApi {

    @POST
    @Path(RegulationSecuredRestApiUrl.DODAJ_ROZPORZADZENIE)
    @PostMapping(RegulationSecuredRestApiUrl.DODAJ_ROZPORZADZENIE)
    ResponseEntity<HttpStatus> addNewRegulation(@RequestBody CreateRegulationDTO regulationDTO, HttpServletRequest request);

    @PUT
    @Path(RegulationSecuredRestApiUrl.MODYFIKUJ_ROZPORZADZENIE)
    @PutMapping(RegulationSecuredRestApiUrl.MODYFIKUJ_ROZPORZADZENIE + "/{id}")
    ResponseEntity<HttpStatus> modifyRegulation(@PathVariable(value = "id") long id, @RequestBody UpdateRegulationDTO request);

    @DELETE
    @Path(RegulationSecuredRestApiUrl.USUN_ROZPORZADZENIE)
    @DeleteMapping(RegulationSecuredRestApiUrl.USUN_ROZPORZADZENIE + "/{id}")
    ResponseEntity<HttpStatus> deleteRegulation(@PathVariable(value = "id") long id);

    @GET
    @Path(RegulationSecuredRestApiUrl.SZCZEGOLY_ROZPORZADZENIA)
    @GetMapping(RegulationSecuredRestApiUrl.SZCZEGOLY_ROZPORZADZENIA + "/{id}")
    RegulationDTO detailsRegulation(@PathVariable(value = "id") long id);

}
