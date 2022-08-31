package pl.kancelaria.AHG.shared.restapi.modules.categoriesRegulations.restApi.secured;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTOrequest;

import javax.ws.rs.*;

@Path(value = CategoryRegulationSecuredRestApiUrl.SCIEZKA_KATEGORIE_ROZPORZADZEN)
@RequestMapping(value = CategoryRegulationSecuredRestApiUrl.SCIEZKA_KATEGORIE_ROZPORZADZEN)
public interface CategoryRegulationSecuredRestApi {

    @POST
    @Path(CategoryRegulationSecuredRestApiUrl.DODAJ_KATEGORIE)
    @PostMapping(CategoryRegulationSecuredRestApiUrl.DODAJ_KATEGORIE)
    ResponseEntity<HttpStatus> dodajKategorieRozporzadzenia(@RequestBody CategoryDTO categoryDTO);

    @DELETE
    @Path(CategoryRegulationSecuredRestApiUrl.USUN_KATEGORIE)
    @DeleteMapping(CategoryRegulationSecuredRestApiUrl.USUN_KATEGORIE + "/{id}")
    ResponseEntity<HttpStatus> usunKategorieRozporzadzenia(@PathVariable(value = "id") long id);

    @GET
    @Path(CategoryRegulationSecuredRestApiUrl.SZCZEGOLY_KATEGORII)
    @GetMapping(CategoryRegulationSecuredRestApiUrl.SZCZEGOLY_KATEGORII + "/{id}")
    CategoryDTOrequest szczegolyKategoriiRozporzadzenia(@PathVariable(value = "id") long id);

    @PUT
    @Path(CategoryRegulationSecuredRestApiUrl.MODYFIKUJ_KATEGORIE)
    @PutMapping(CategoryRegulationSecuredRestApiUrl.MODYFIKUJ_KATEGORIE + "/{id}")
    CategoryDTOrequest modyfikujKategorieRozporzadzenia(@PathVariable(value = "id") long id, @Validated @RequestBody CategoryDTOrequest request);

}
