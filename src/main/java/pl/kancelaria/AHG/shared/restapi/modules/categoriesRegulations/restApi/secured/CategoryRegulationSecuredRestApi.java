package pl.kancelaria.AHG.shared.restapi.modules.categoriesRegulations.restApi.secured;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTOrequest;

@RequestMapping(value = CategoryRegulationSecuredRestApiUrl.SCIEZKA_KATEGORIE_ROZPORZADZEN)
public interface CategoryRegulationSecuredRestApi {

    @PostMapping(CategoryRegulationSecuredRestApiUrl.DODAJ_KATEGORIE)
    ResponseEntity<HttpStatus> dodajKategorie(@RequestBody CategoryDTO categoryDTO);

    @DeleteMapping(CategoryRegulationSecuredRestApiUrl.USUN_KATEGORIE + "/{id}")
    ResponseEntity<HttpStatus> usunKategorie(@PathVariable(value = "id")long id);

    @GetMapping (CategoryRegulationSecuredRestApiUrl.SZCZEGOLY_KATEGORII + "/{id}")
    CategoryDTOrequest szczegolyKategorii(@PathVariable(value = "id") long id);

}
