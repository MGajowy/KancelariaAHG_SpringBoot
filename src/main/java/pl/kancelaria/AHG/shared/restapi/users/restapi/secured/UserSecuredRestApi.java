package pl.kancelaria.AHG.shared.restapi.users.restapi.secured;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kancelaria.AHG.shared.restapi.modules.document.restApi.user.dto.UserListDocumentDTO;
import pl.kancelaria.AHG.user.dto.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;


@Path(value = UserSecuredRestApiUrl.SCIEZKA_UZYTKOWNICY)
@RequestMapping(value = UserSecuredRestApiUrl.SCIEZKA_UZYTKOWNICY)
//@Secured()
public interface UserSecuredRestApi {

    @GET
    @GetMapping(UserSecuredRestApiUrl.LISTA_UZYTWONIKOW)
    @Path(UserSecuredRestApiUrl.LISTA_UZYTWONIKOW)
    UserListDTO getUserList(@QueryParam("term") String term);

    @GET
    @GetMapping(UserSecuredRestApiUrl.UZYTKOWNICY_DOKUMENTY)
    @Path(UserSecuredRestApiUrl.UZYTKOWNICY_DOKUMENTY)
    UserListDocumentDTO getAllUsers();

    @GetMapping(UserSecuredRestApiUrl.LISTA_UZYTWONIKOW  + "/{pageNumber}" + "/{pageSize}")
    UserListDTO getUserListByNameAndPage(
            @QueryParam("term") String term,
            @PathVariable("pageNumber") final Integer pageNumber,
            @PathVariable("pageSize") final Integer pageSize);

    @POST
    @PostMapping(UserSecuredRestApiUrl.DODAJ_UZYTKOWNIKA)
    @Path(UserSecuredRestApiUrl.DODAJ_UZYTKOWNIKA)
    ResponseEntity<HttpStatus> createNewUser(@RequestBody AddUserDTO addUserDTO, HttpServletRequest request);

    @PUT
    @PutMapping(UserSecuredRestApiUrl.MODYFIKUJ_UZYTKOWNIKA + "/{id}")
    @Path(UserSecuredRestApiUrl.MODYFIKUJ_UZYTKOWNIKA)
    UserDTO modifyUser(@PathVariable(value = "id") long id, @Validated @RequestBody UserDTO userDTO);

    @DELETE
    @DeleteMapping(UserSecuredRestApiUrl.USUN_UZYTKOWNIKA + "/{id}")
    @Path(UserSecuredRestApiUrl.USUN_UZYTKOWNIKA)
    ResponseEntity<HttpStatus> deleteUser(long id);

    @POST
    @PostMapping(UserSecuredRestApiUrl.WYSLIJ_EMAIL_AKTYWACYJNY)
    @Path(UserSecuredRestApiUrl.WYSLIJ_EMAIL_AKTYWACYJNY)
    Boolean userActivation(@RequestBody LocationDTO locationDTO);

//    @GET
//    @GetMapping (UserSecuredRestApiUrl.WYSLIJ_EMAIL_AKTYWACYJNY + "/{id}")
//    @Path(UserSecuredRestApiUrl.WYSLIJ_EMAIL_AKTYWACYJNY)
//    Boolean wyslijMailAktywacyjny(@PathVariable(value = "id") long id);

    @POST
    @PostMapping(UserSecuredRestApiUrl.DEZAKTUWUJ_UZYTKOWNIKA + "/{id}")
    @Path(UserSecuredRestApiUrl.DEZAKTUWUJ_UZYTKOWNIKA)
    Boolean userDeactivation(long id, HttpServletRequest request);

    @GET
    @GetMapping(UserSecuredRestApiUrl.SZCZEGOLY_UZYTKOWNIKA + "/{id}")
    @Path(UserSecuredRestApiUrl.SZCZEGOLY_UZYTKOWNIKA)
    UserDTO userDetails(@PathVariable(value = "id") long id);

    //todo rest niepodłączony
    @GET
    @GetMapping(UserSecuredRestApiUrl.UZYTKOWNICY_PO_STAN)
    UserListDTO getUserListOfStatus(@QueryParam("stan") String stan);
}
