package pl.kancelaria.AHG.shared.restapi.users.restapi.secured;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kancelaria.AHG.user.dto.*;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;


@Path(value = UserSecuredRestApiUrl.SCIEZKA_UZYTKOWNICY)
@RequestMapping (value = UserSecuredRestApiUrl.SCIEZKA_UZYTKOWNICY )
//@Secured()
public interface UserSecuredRestApi {

    @GET
    @GetMapping(UserSecuredRestApiUrl.LISTA_UZYTWONIKOW)
    @Path(UserSecuredRestApiUrl.LISTA_UZYTWONIKOW)
    UserListDTO pobierzListeUzytkownikowDto(@QueryParam("term") String term);

    @POST
    @PostMapping(UserSecuredRestApiUrl.DODAJ_UZYTKOWNIKA)
    @Path(UserSecuredRestApiUrl.DODAJ_UZYTKOWNIKA)
    ResponseEntity<HttpStatus> utworzUzytkownika(@RequestBody AddUserDTO addUserDTO, HttpServletRequest request);

    @PUT
    @PutMapping(UserSecuredRestApiUrl.MODYFIKUJ_UZYTKOWNIKA + "/{id}")
    @Path(UserSecuredRestApiUrl.MODYFIKUJ_UZYTKOWNIKA)
    UserDTO modyfikujUzytkownika( @PathVariable(value = "id")long id, @Validated @RequestBody UserDTO userDTO);

    @DELETE
    @DeleteMapping(UserSecuredRestApiUrl.USUN_UZYTKOWNIKA + "/{id}")
    @Path(UserSecuredRestApiUrl.USUN_UZYTKOWNIKA)
    ResponseEntity<HttpStatus> usunUzytkownika(long id);

    @POST
    @PostMapping (UserSecuredRestApiUrl.WYSLIJ_EMAIL_AKTYWACYJNY)
    @Path(UserSecuredRestApiUrl.WYSLIJ_EMAIL_AKTYWACYJNY)
    Boolean wyslijMailAktywacyjny(@RequestBody LocationDTO locationDTO);

//    @GET
//    @GetMapping (UserSecuredRestApiUrl.WYSLIJ_EMAIL_AKTYWACYJNY + "/{id}")
//    @Path(UserSecuredRestApiUrl.WYSLIJ_EMAIL_AKTYWACYJNY)
//    Boolean wyslijMailAktywacyjny(@PathVariable(value = "id") long id);

    @POST
    @PostMapping(UserSecuredRestApiUrl.DEZAKTUWUJ_UZYTKOWNIKA + "/{id}")
    @Path(UserSecuredRestApiUrl.DEZAKTUWUJ_UZYTKOWNIKA)
    Boolean dezaktywacjaUzytkownika(long id, HttpServletRequest request);

    @GET
    @GetMapping(UserSecuredRestApiUrl.SZCZEGOLY_UZYTKOWNIKA + "/{id}")
    @Path(UserSecuredRestApiUrl.SZCZEGOLY_UZYTKOWNIKA)
    UserDTO szczegolyUzytkownika(@PathVariable(value = "id")long id);

    //todo rest niepodłączony
    @GET
    @GetMapping(UserSecuredRestApiUrl.UZYTKOWNICY_PO_STAN)
    UserListDTO pobierzListeUzytkownikowPoStan(@QueryParam("stan")String stan);
}
