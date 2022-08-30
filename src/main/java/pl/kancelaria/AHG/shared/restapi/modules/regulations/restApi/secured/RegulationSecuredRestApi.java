package pl.kancelaria.AHG.shared.restapi.modules.regulations.restApi.secured;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kancelaria.AHG.modules.regulations.dto.CreateRegulationDTO;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path(value = RegulationSecuredRestApiUrl.SCIEZKA_ROZPORZADZENIA)
@RequestMapping(value = RegulationSecuredRestApiUrl.SCIEZKA_ROZPORZADZENIA)
public interface RegulationSecuredRestApi {

    @POST
    @Path(RegulationSecuredRestApiUrl.DODAJ_ROZPORZADZENIE)
    @PostMapping(RegulationSecuredRestApiUrl.DODAJ_ROZPORZADZENIE)
    ResponseEntity<HttpStatus> dodajRozporzadzenie(@RequestBody CreateRegulationDTO regulationDTO, HttpServletRequest request);
}
