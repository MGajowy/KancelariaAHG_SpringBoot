package pl.kancelaria.AHG.shared.restapi.users.restapi.secured;

//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kancelaria.AHG.user.dto.UserListDTO;

import javax.ws.rs.GET;
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
    UserListDTO pobierzListeUzytkownikowDto();
}
