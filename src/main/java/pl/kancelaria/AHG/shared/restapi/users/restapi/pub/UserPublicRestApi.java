package pl.kancelaria.AHG.shared.restapi.users.restapi.pub;

import org.springframework.web.bind.annotation.*;
import pl.kancelaria.AHG.user.dto.ResetPasswordDTO;
import pl.kancelaria.AHG.user.role.RolesName;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;


@Path(value = UserPublicRestApiUrl.SCIEZKA_UZYTKOWNICY)
@RequestMapping(value = UserPublicRestApiUrl.SCIEZKA_UZYTKOWNICY)

public interface UserPublicRestApi {

    @GET
    @GetMapping(UserPublicRestApiUrl.WERYFIKUJ_TOKEN + "/{token}")
    @Path(UserPublicRestApiUrl.WERYFIKUJ_TOKEN)
    Boolean checkToken(@PathVariable(value = "token") String token);

    @POST
    @PostMapping(UserPublicRestApiUrl.WYSLIJ_EMAIL_RESET_HASLA)
    @Path(UserPublicRestApiUrl.WYSLIJ_EMAIL_RESET_HASLA)
    Boolean passwordReset(@RequestBody ResetPasswordDTO dto);

    @GET
    @GetMapping(UserPublicRestApiUrl.ROLE)
    @Path(UserPublicRestApiUrl.ROLE)
    List<RolesName> getRoles( HttpServletRequest request);

}


