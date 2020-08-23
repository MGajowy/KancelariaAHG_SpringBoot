package pl.kancelaria.AHG.user.restApi.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.comon.model.users.user.UserOB;
import pl.kancelaria.AHG.user.dto.RegistrationDTO;
import pl.kancelaria.AHG.user.dto.UserDTO;
import pl.kancelaria.AHG.user.dto.UserListDTO;
import pl.kancelaria.AHG.user.services.UserListService;
import pl.kancelaria.AHG.user.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

/**
 * @author Michal
 * @created 29/07/2020
 */
@RestController
public class UserSecuredRestApi implements pl.kancelaria.AHG.shared.restapi.users.restapi.secured.UserSecuredRestApi {

    private UserListService userListService;
    private UserService userService;

@Autowired
    public UserSecuredRestApi(UserListService userListService, UserService UserService) {
        this.userListService = userListService;
    this.userService = UserService;
}

    @Override
    public UserListDTO pobierzListeUzytkownikowDto() {
        return userListService.pobierzListeUzytkownikow();
    }

    @Override
    public Response utworzUzytkownika(UserOB user, HttpServletRequest request) {
        userService.utworzNowegoUzytkownika(user, request);
        return null;
    }

    @Override
    public String login() {
        return null;
    }

    @Override
    public Response rejestracjaNowegoUzytkownika(RegistrationDTO registrationDTO) {
    userService.rejestracjaNowegoUzytkownika(registrationDTO);
    return Response.status(200,"OK").build();
    }
}
