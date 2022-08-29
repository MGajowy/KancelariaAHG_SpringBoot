package pl.kancelaria.AHG.shared.restapi.modules.categoriesRegulations.restApi.pub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kancelaria.AHG.modules.categories.dto.CategoryListDTO;
import pl.kancelaria.AHG.shared.restapi.modules.resolutions.restapi.pub.ResolutionPublicRestApiUrl;

@RequestMapping(value = CategoryRegulationPublicRestApiUrl.SCIEZKA_KATEGORIE_ROZPORZADZEN)
public interface CategoryRegulationPublicRestApi {

    @GetMapping (CategoryRegulationPublicRestApiUrl.WSZYSTKIE_KATEGORIE_ROZPORZADZEN)
    CategoryListDTO pobierzListeKategorii();
}
