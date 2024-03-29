package pl.kancelaria.AHG.shared.restapi.modules.regulations.restApi.pub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kancelaria.AHG.modules.regulations.dto.RegulationListDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path(value = RegulationPublicRestApiUrl.SCIEZKA_ROZPORZADZENIA)
@RequestMapping(value = RegulationPublicRestApiUrl.SCIEZKA_ROZPORZADZENIA)
public interface RegulationPublicRestApi {

    @GET
    @Path(RegulationPublicRestApiUrl.ROZPORZADZENIA_LISTA_PO_OPISIE)
    @GetMapping(RegulationPublicRestApiUrl.ROZPORZADZENIA_LISTA_PO_OPISIE)
    RegulationListDTO getRegulationsListByName(@QueryParam("name") String name);

    @GET
    @Path(RegulationPublicRestApiUrl.ROZPORZADZENIA_LISTA_PO_OPISIE)
    @GetMapping(RegulationPublicRestApiUrl.ROZPORZADZENIA_LISTA_PO_OPISIE  + "/{pageNumber}" + "/{pageSize}")
    RegulationListDTO getRegulationsListByNameAndPage(@QueryParam("name") String name, @PathVariable("pageNumber") final Integer pageNumber, @PathVariable("pageSize") final Integer pageSize );

}
