package pl.kancelaria.AHG.shared.restapi.modules.categoriesRegulations.restApi.pub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kancelaria.AHG.modules.categories.dto.CategoryListDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path(value = CategoryRegulationPublicRestApiUrl.SCIEZKA_KATEGORIE_ROZPORZADZEN)
@RequestMapping(value = CategoryRegulationPublicRestApiUrl.SCIEZKA_KATEGORIE_ROZPORZADZEN)
public interface CategoryRegulationPublicRestApi {

    @GET
    @Path(CategoryRegulationPublicRestApiUrl.WSZYSTKIE_KATEGORIE_ROZPORZADZEN)
    @GetMapping(CategoryRegulationPublicRestApiUrl.WSZYSTKIE_KATEGORIE_ROZPORZADZEN)
    CategoryListDTO pobierzListeKategorii();
}
