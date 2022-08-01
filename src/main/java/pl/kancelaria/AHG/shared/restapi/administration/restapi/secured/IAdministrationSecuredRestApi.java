package pl.kancelaria.AHG.shared.restapi.administration.restapi.secured;

//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kancelaria.AHG.administration.dto.EventLogListDTO;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.IOException;


@Path(AdministrationSecuredRestApiUrl.SCIEZKA_ADMINISTRACJA)
@RequestMapping(value = AdministrationSecuredRestApiUrl.SCIEZKA_ADMINISTRACJA)
public interface IAdministrationSecuredRestApi {

    @GET
    @GetMapping(AdministrationSecuredRestApiUrl.DZIENNIK_ZDARZEN)
    @Path(AdministrationSecuredRestApiUrl.DZIENNIK_ZDARZEN)
    EventLogListDTO pobierzDziennikZdarzenDto();

    @GetMapping(AdministrationSecuredRestApiUrl.EXPORT_TO_PDF)
    void exportToPDF(HttpServletResponse response) throws IOException;
}
