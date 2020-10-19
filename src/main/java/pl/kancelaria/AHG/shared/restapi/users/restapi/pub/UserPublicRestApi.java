package pl.kancelaria.AHG.shared.restapi.users.restapi.pub;

import org.springframework.web.bind.annotation.*;
import pl.kancelaria.AHG.shared.restapi.modules.categories.restapi.pub.CategoryPublicRestApi;
import pl.kancelaria.AHG.shared.restapi.users.restapi.secured.UserSecuredRestApiUrl;
import pl.kancelaria.AHG.user.dto.UserPasswordDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author Michal
 * @created 29/07/2020
 */
@Path(value = UserPublicRestApiUrl.SCIEZKA_UZYTKOWNICY)
@RequestMapping(value = UserPublicRestApiUrl.SCIEZKA_UZYTKOWNICY)

public interface UserPublicRestApi  {

    @GET
    @GetMapping(UserSecuredRestApiUrl.WERYFIKUJ_TOKEN + "/{token}")
    @Path(UserSecuredRestApiUrl.WERYFIKUJ_TOKEN)
    Boolean weryfikujToken(@PathVariable(value = "token") String token);
}
