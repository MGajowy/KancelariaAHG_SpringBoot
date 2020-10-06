package pl.kancelaria.AHG.shared.restapi.modules.categories.restapi.secured;

//import org.springframework.security.access.annotation.Secured;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTOrequest;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTOresponse;

import javax.ws.rs.*;

/**
 * @author Michal
 * @created 29/07/2020
 */
@Path(value = CategorySecuredRestApiUrl.SCIEZKA_KATEGORIE)
@RequestMapping (value = CategorySecuredRestApiUrl.SCIEZKA_KATEGORIE)
//@Secured()
public interface CategorySecuredRestApi {

    @POST
    @PostMapping(CategorySecuredRestApiUrl.DODAJ_KATEGORIE)
    @Path(CategorySecuredRestApiUrl.DODAJ_KATEGORIE)
    ResponseEntity<HttpStatus> dodajKategorie(@RequestBody CategoryDTO categoryDTO);

    @PUT
    @PutMapping (CategorySecuredRestApiUrl.MODYFIKUJ_KATEGORIE  + "/{id}")
    @Path(CategorySecuredRestApiUrl.MODYFIKUJ_KATEGORIE)
    CategoryDTOrequest modyfikujKategorie( @PathVariable(value = "id")long id, @Validated @RequestBody CategoryDTOrequest request);

    @DELETE
    @DeleteMapping(CategorySecuredRestApiUrl.USUN_KATEGORIE + "/{id}")
    @Path(CategorySecuredRestApiUrl.USUN_KATEGORIE)
    ResponseEntity<HttpStatus> usunKategorie(long id);

    @GET
    @GetMapping(CategorySecuredRestApiUrl.SZCZEGOLY_KATEGORII + "/{id}")
    @Path(CategorySecuredRestApiUrl.SZCZEGOLY_KATEGORII)
    CategoryDTOrequest szczegolyKategorii(@PathVariable(value = "id") long id);
}
