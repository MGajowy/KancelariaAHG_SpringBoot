package pl.kancelaria.AHG.user.restApi.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.user.dto.*;
import pl.kancelaria.AHG.user.services.*;

import javax.servlet.http.HttpServletRequest;

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
    public ResponseEntity<HttpStatus> utworzUzytkownika(AddUserDTO addUserDTO, HttpServletRequest request) {
        userService.utworzNowegoUzytkownika(addUserDTO, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public UserDTO modyfikujUzytkownika() {
        return null;
    }

    @Override
    public ResponseEntity<HttpStatus> usunUzytkownika(@PathVariable("id") long id) {
        deleteUserService.usunUzytkownika(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @Override
//    public Boolean wyslijMailAktywacyjny(UserDTO user, HttpServletRequest request) {
//       userService.aktywujUzytkownika(user, request);
//        return true;
//    }

    @Override
    public Boolean wyslijMailAktywacyjny(@PathVariable("id") long id, HttpServletRequest request) {
        userService.aktywujUzytkownika(id, request);
        return true;
    }

    @Override
    public Boolean weryfikujToken(String token) {
        userService.weryfikujToken(token);
        return true;
    }
}
