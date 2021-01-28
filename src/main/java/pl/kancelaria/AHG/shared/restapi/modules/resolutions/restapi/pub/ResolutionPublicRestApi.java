package pl.kancelaria.AHG.shared.restapi.modules.resolutions.restapi.pub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kancelaria.AHG.modules.categories.dto.CategoryListDTO;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionListDTO;
import pl.kancelaria.AHG.shared.restapi.modules.categories.restapi.pub.CategoryPublicRestApiUrl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author Michal
 * @created 29/07/2020
 */
@Path(value = ResolutionPublicRestApiUrl.SCIEZKA_UCHWALY )
@RequestMapping(value = ResolutionPublicRestApiUrl.SCIEZKA_UCHWALY)
public interface ResolutionPublicRestApi {

    @GET
    @GetMapping(ResolutionPublicRestApiUrl.UCHWALY_LISTA)
    @Path(ResolutionPublicRestApiUrl.UCHWALY_LISTA)
    ResolutionListDTO pobierzListeUchwalDto();
}
