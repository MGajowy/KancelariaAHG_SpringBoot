package pl.kancelaria.AHG.shared.restapi.administration.restapi.secured;

//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kancelaria.AHG.administration.dto.EventLogListDTO;
import pl.kancelaria.AHG.modules.categories.dto.CategoryListDTO;
import pl.kancelaria.AHG.shared.restapi.modules.categories.restapi.pub.CategoryPublicRestApiUrl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author Michal
 * @created 29/07/2020
 */
@Path(AdministrationSecuredRestApiUrl.SCIEZKA_ADMINISTRACJA)
@RequestMapping(value = AdministrationSecuredRestApiUrl.SCIEZKA_ADMINISTRACJA)
public interface IAdministrationSecuredRestApi {

    @GET
    @GetMapping(AdministrationSecuredRestApiUrl.DZIENNIK_ZDARZEN)
    @Path(AdministrationSecuredRestApiUrl.DZIENNIK_ZDARZEN)
    EventLogListDTO pobierzDziennikZdarzenDto();
}
