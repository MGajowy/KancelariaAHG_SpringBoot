package pl.kancelaria.AHG.shared.restapi.users.restapi.secured;

//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pl.kancelaria.AHG.comon.model.users.user.UserOB;
import pl.kancelaria.AHG.user.dto.RegistrationDTO;
import pl.kancelaria.AHG.user.dto.UserDTO;
import pl.kancelaria.AHG.user.dto.UserListDTO;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

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
    Response utworzUzytkownika(UserOB userOB, HttpServletRequest request);

    @POST
    @PostMapping(UserSecuredRestApiUrl.MODYFIKUJ_UZYTKOWNIKA)
    @Path(UserSecuredRestApiUrl.MODYFIKUJ_UZYTKOWNIKA)
    UserDTO modyfikujUzytkownika();

    @DELETE
    @DeleteMapping(UserSecuredRestApiUrl.USUN_UZYTKOWNIKA)
    @Path(UserSecuredRestApiUrl.USUN_UZYTKOWNIKA)
    UserDTO usunUzytkownika();

    @GET
    @GetMapping(UserSecuredRestApiUrl.LOGIN)
    @Path(UserSecuredRestApiUrl.LOGIN)
    String login();

    @POST
    @PostMapping
    @Path(UserSecuredRestApiUrl.REJESTRACJA)
    Response rejestracjaNowegoUzytkownika(@RequestBody RegistrationDTO registrationDTO);






}
