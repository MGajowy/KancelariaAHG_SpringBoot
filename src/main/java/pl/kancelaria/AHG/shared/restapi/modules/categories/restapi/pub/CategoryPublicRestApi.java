package pl.kancelaria.AHG.shared.restapi.modules.categories.restapi.pub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kancelaria.AHG.modules.categories.dto.CategoryListDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;


@Path(value = CategoryPublicRestApiUrl.SCIEZKA_KATEGORIE)
@RequestMapping(value =CategoryPublicRestApiUrl.SCIEZKA_KATEGORIE)
public interface CategoryPublicRestApi {

    @GET
    @GetMapping(CategoryPublicRestApiUrl.TEST)
    @Path(CategoryPublicRestApiUrl.TEST)
    String getStr();

    @GET
    @GetMapping(CategoryPublicRestApiUrl.WSZYSTKIE_KATEGORIE)
    @Path(CategoryPublicRestApiUrl.WSZYSTKIE_KATEGORIE)
    CategoryListDTO pobierzListCategoryDto();

    @GET
    @GetMapping(CategoryPublicRestApiUrl.WYSZUKAJ_KATEGORIE)
    @Path(CategoryPublicRestApiUrl.WYSZUKAJ_KATEGORIE)
    CategoryListDTO wyszukajKategorie(@QueryParam("term") String term);



}
