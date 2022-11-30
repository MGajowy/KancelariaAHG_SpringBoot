package pl.kancelaria.AHG.shared.restapi.modules.categories.restapi.pub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kancelaria.AHG.modules.categories.dto.CategoryListDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;


@Path(value = CategoryPublicRestApiUrl.SCIEZKA_KATEGORIE)
@RequestMapping(value =CategoryPublicRestApiUrl.SCIEZKA_KATEGORIE)
public interface CategoryPublicRestApi {


    @GET
    @GetMapping(CategoryPublicRestApiUrl.WSZYSTKIE_KATEGORIE)
    @Path(CategoryPublicRestApiUrl.WSZYSTKIE_KATEGORIE)
    CategoryListDTO getCategoryList();

    @GET
    @GetMapping(CategoryPublicRestApiUrl.WYSZUKAJ_KATEGORIE)
    @Path(CategoryPublicRestApiUrl.WYSZUKAJ_KATEGORIE)
    CategoryListDTO searchCategories(@QueryParam("term") String term);

    //todo rest niepodłączony - metoda testowa
    @GET
    @GetMapping(CategoryPublicRestApiUrl.WYSZUKAJ_KATEGORIE_PO_NAZWA)
    @Path(CategoryPublicRestApiUrl.WYSZUKAJ_KATEGORIE_PO_NAZWA)
    List<String> getCategoryListByName2(@QueryParam("rodzajKategorii") String rodzajKategorii);


}
