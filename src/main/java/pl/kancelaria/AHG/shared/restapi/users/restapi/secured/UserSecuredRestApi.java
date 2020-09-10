package pl.kancelaria.AHG.shared.restapi.users.restapi.secured;

//import org.springframework.security.access.annotation.Secured;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kancelaria.AHG.user.dto.*;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author Michal
 * @created 29/07/2020
 */
@Path(value = UserSecuredRestApiUrl.SCIEZKA_UZYTKOWNICY)
@RequestMapping (value = UserSecuredRestApiUrl.SCIEZKA_UZYTKOWNICY )
//@Secured()
public interface UserSecuredRestApi {

    @GET
    @GetMapping(UserSecuredRestApiUrl.LISTA_UZYTWONIKOW)
    @Path(UserSecuredRestApiUrl.LISTA_UZYTWONIKOW)
    UserListDTO pobierzListeUzytkownikowDto();

    @POST
    @PostMapping(UserSecuredRestApiUrl.DODAJ_UZYTKOWNIKA)
    @Path(UserSecuredRestApiUrl.DODAJ_UZYTKOWNIKA)
    ResponseEntity<HttpStatus> utworzUzytkownika(@RequestBody AddUserDTO addUserDTO);

    @POST
    @PostMapping(UserSecuredRestApiUrl.MODYFIKUJ_UZYTKOWNIKA)
    @Path(UserSecuredRestApiUrl.MODYFIKUJ_UZYTKOWNIKA)
    UserDTO modyfikujUzytkownika();

    @DELETE
    @DeleteMapping(UserSecuredRestApiUrl.USUN_UZYTKOWNIKA + "/{id}")
    @Path(UserSecuredRestApiUrl.USUN_UZYTKOWNIKA)
    ResponseEntity<HttpStatus> usunUzytkownika(long id);







}
