package pl.kancelaria.AHG.user.restApi.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final UserDetailsServiceImpl userDetailsService;



    @Autowired
    public UserSecuredRestApi(UserListService userListService, UserService userService, AddUserService addUserService, DeleteUserService deleteUserService, ModifyUserService modifyUserService, UserDetailsServiceImpl userDetailsService) {
        this.userListService = userListService;
        this.userService = userService;
        this.addUserService = addUserService;
        this.deleteUserService = deleteUserService;
        this.modifyUserService = modifyUserService;
        this.userDetailsService = userDetailsService;
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
    public UserDTO modyfikujUzytkownika(long id, UserDTO userDTO) {
        modifyUserService.modyfikujUzytkownika(id, userDTO );
        return userDTO;
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
    public Boolean wyslijMailAktywacyjny( LocationDTO locationDTO) {
        userService.aktywujUzytkownika(locationDTO);
        return true;
    }

    @Override
    public Boolean dezaktywacjaUzytkownika(@PathVariable("id") long id, HttpServletRequest request) {
        userService.dezaktuwujUzytkownika(id);
        return true;
    }

    @Override
    public UserDTO szczegolyUzytkownika(long id) {
       UserDTO userDTO = userDetailsService.szczegoly(id);
        return userDTO;
    }
}
