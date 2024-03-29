package pl.kancelaria.AHG.shared.restapi.modules.categories.restapi.secured;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTOrequest;
import pl.kancelaria.AHG.modules.categories.dto.CategoryListDTO;

import javax.ws.rs.*;

@Path(value = CategorySecuredRestApiUrl.SCIEZKA_KATEGORIE)
@RequestMapping(value = CategorySecuredRestApiUrl.SCIEZKA_KATEGORIE)
public interface CategorySecuredRestApi {

    @POST
    @PostMapping(CategorySecuredRestApiUrl.DODAJ_KATEGORIE)
    @Path(CategorySecuredRestApiUrl.DODAJ_KATEGORIE)
    ResponseEntity<HttpStatus> addNewCategories(@RequestBody CategoryDTO categoryDTO);

    @PUT
    @PutMapping(CategorySecuredRestApiUrl.MODYFIKUJ_KATEGORIE + "/{id}")
    @Path(CategorySecuredRestApiUrl.MODYFIKUJ_KATEGORIE)
    CategoryDTOrequest modifyCategories(@PathVariable(value = "id") long id, @Validated @RequestBody CategoryDTOrequest request);

    @DELETE
    @DeleteMapping(CategorySecuredRestApiUrl.USUN_KATEGORIE + "/{id}")
    @Path(CategorySecuredRestApiUrl.USUN_KATEGORIE)
    ResponseEntity<HttpStatus> deleteCategories(long id);

    @GET
    @GetMapping(CategorySecuredRestApiUrl.SZCZEGOLY_KATEGORII + "/{id}")
    @Path(CategorySecuredRestApiUrl.SZCZEGOLY_KATEGORII)
    CategoryDTOrequest getCategoriesById(@PathVariable(value = "id") long id);

    //todo rest niepodłączony
    @GET
    @GetMapping(CategorySecuredRestApiUrl.STATUS_KATEGORII)
    CategoryListDTO searchCategoriesByStatus(@RequestBody Boolean status);
}
