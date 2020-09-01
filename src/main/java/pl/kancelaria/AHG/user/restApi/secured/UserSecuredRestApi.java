package pl.kancelaria.AHG.user.restApi.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.comon.model.users.user.UserOB;
import pl.kancelaria.AHG.user.dto.LoginDTO;
import pl.kancelaria.AHG.user.dto.RegistrationDTO;
import pl.kancelaria.AHG.user.dto.UserDTO;
import pl.kancelaria.AHG.user.dto.UserListDTO;
import pl.kancelaria.AHG.user.services.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

/**
 * @author Michal
 * @created 29/07/2020
 */
@RestController
public class UserSecuredRestApi implements pl.kancelaria.AHG.shared.restapi.users.restapi.secured.UserSecuredRestApi {

    private final UserListService userListService;
    private final UserService userService;
    private final AddUserService addUserService;
    private final DeleteUserService deleteUserService;
    private final ModifyUserService modifyUserService;

    @Autowired
    public UserSecuredRestApi(UserListService userListService, UserService userService, AddUserService addUserService, DeleteUserService deleteUserService, ModifyUserService modifyUserService) {
        this.userListService = userListService;
        this.userService = userService;
        this.addUserService = addUserService;
        this.deleteUserService = deleteUserService;
        this.modifyUserService = modifyUserService;
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
    public UserDTO modyfikujUzytkownika() {
        return null;
    }

    @Override
    public UserDTO usunUzytkownika() {
        return null;
    }
}
