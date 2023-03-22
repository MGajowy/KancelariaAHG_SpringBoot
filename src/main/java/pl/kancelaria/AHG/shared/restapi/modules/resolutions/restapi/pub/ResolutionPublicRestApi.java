package pl.kancelaria.AHG.shared.restapi.modules.resolutions.restapi.pub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionListDTO;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionListOfCategoryDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;


@Path(value = ResolutionPublicRestApiUrl.SCIEZKA_UCHWALY )
@RequestMapping(value = ResolutionPublicRestApiUrl.SCIEZKA_UCHWALY)
public interface ResolutionPublicRestApi {

    @GET
    @GetMapping(ResolutionPublicRestApiUrl.UCHWALY_LISTA)
    @Path(ResolutionPublicRestApiUrl.UCHWALY_LISTA)
    ResolutionListDTO getResolutionList();

    @GET
    @GetMapping(ResolutionPublicRestApiUrl.UCHWALY_LISTA_PO_KATEGORII + "/{id}")
    ResolutionListOfCategoryDTO getResolutionListByCategories(@PathVariable(value = "id")long id);

    // todo niepodłaczony Rest
    @GetMapping(ResolutionPublicRestApiUrl.UCHWALY_LISTA_CB)
    ResolutionListDTO getResolutionListCB();

    @GetMapping(ResolutionPublicRestApiUrl.UCHWALY_LISTA_PO_NAZWA)
    ResolutionListDTO getResolutionListByDescription(@QueryParam("term") String term);

    @GetMapping(ResolutionPublicRestApiUrl.UCHWALY_LISTA_PO_NAZWA + "/{pageNumber}" + "/{pageSize}")
    ResolutionListDTO getResolutionListByDescriptionAndPages(
            @QueryParam("term") String term,
            @PathVariable("pageNumber") final Integer pageNumber,
            @PathVariable("pageSize") final Integer pageSize);
}
