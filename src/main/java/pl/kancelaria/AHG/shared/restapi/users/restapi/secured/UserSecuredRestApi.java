package pl.kancelaria.AHG.shared.restapi.users.restapi.secured;

//import org.springframework.security.access.annotation.Secured;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kancelaria.AHG.comon.model.users.user.UserOB;
import pl.kancelaria.AHG.user.dto.*;

import javax.servlet.http.HttpServletRequest;
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
    ResponseEntity<HttpStatus> utworzUzytkownika(@RequestBody AddUserDTO addUserDTO, HttpServletRequest request);

    @POST
    @PostMapping(UserSecuredRestApiUrl.MODYFIKUJ_UZYTKOWNIKA)
    @Path(UserSecuredRestApiUrl.MODYFIKUJ_UZYTKOWNIKA)
    UserDTO modyfikujUzytkownika();

    @DELETE
    @DeleteMapping(UserSecuredRestApiUrl.USUN_UZYTKOWNIKA + "/{id}")
    @Path(UserSecuredRestApiUrl.USUN_UZYTKOWNIKA)
    ResponseEntity<HttpStatus> usunUzytkownika(long id);

//    @GET
//    @GetMapping(UserSecuredRestApiUrl.WYSLIJ_EMAIL_AKTYWACYJNY)
//    @Path(UserSecuredRestApiUrl.WYSLIJ_EMAIL_AKTYWACYJNY)
//    Boolean wyslijMailAktywacyjny(@RequestBody UserDTO user, HttpServletRequest request);

    @GET
    @GetMapping(UserSecuredRestApiUrl.WYSLIJ_EMAIL_AKTYWACYJNY + "/{id}")
    @Path(UserSecuredRestApiUrl.WYSLIJ_EMAIL_AKTYWACYJNY)
    Boolean wyslijMailAktywacyjny(long id, HttpServletRequest request);

    @GET
    @GetMapping(UserSecuredRestApiUrl.WERYFIKUJ_TOKEN)
    @Path(UserSecuredRestApiUrl.WERYFIKUJ_TOKEN)
    Boolean weryfikujToken(@RequestParam String token);







}
