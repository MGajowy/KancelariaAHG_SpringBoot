package pl.kancelaria.AHG.user.restApi.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.shared.restapi.modules.document.restApi.user.dto.UserListDocumentDTO;
import pl.kancelaria.AHG.user.dto.*;
import pl.kancelaria.AHG.user.services.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class UserSecuredRestApi implements pl.kancelaria.AHG.shared.restapi.users.restapi.secured.UserSecuredRestApi {

    private final UserListService userListService;
    private final UserService userService;
    private final AddUserService addUserService;
    private final DeleteUserService deleteUserService;
    private final ModifyUserService modifyUserService;
    private final UserDetailsServiceImpl userDetailsService;


    @Autowired
    public UserSecuredRestApi(UserListService userListService,
                              UserService userService,
                              AddUserService addUserService,
                              DeleteUserService deleteUserService,
                              ModifyUserService modifyUserService,
                              UserDetailsServiceImpl userDetailsService) {
        this.userListService = userListService;
        this.userService = userService;
        this.addUserService = addUserService;
        this.deleteUserService = deleteUserService;
        this.modifyUserService = modifyUserService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public UserListDTO getUserList(String term) {
        return userListService.getUserList(term);
    }

    @Override
    public UserListDocumentDTO getAllUsers() {
        return userListService.getAllUsers();
    }

    @Override
    public UserListDTO getUserListByNameAndPage(String term, Integer pageNumber, Integer pageSize) {
        return userListService.getUserListByNameAndPage(term, pageNumber, pageSize);
    }

    @Override
    public ResponseEntity<HttpStatus> createNewUser(AddUserDTO addUserDTO, HttpServletRequest request) {
        return userService.createNewUser(addUserDTO);
    }

    @Override
    public UserDTO modifyUser(long id, UserDTO userDTO) {
        modifyUserService.modifyUser(id, userDTO);
        return userDTO;
    }

    @Override
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        return deleteUserService.deleteUser(id);
    }

//    @Override
//    public Boolean wyslijMailAktywacyjny(UserDTO user, HttpServletRequest request) {
//       userService.aktywujUzytkownika(user, request);
//        return true;
//    }

    @Override
    public Boolean userActivation(LocationDTO locationDTO) {
        return userService.userActivation(locationDTO);
    }

    @Override
    public Boolean userDeactivation(@PathVariable("id") long id, HttpServletRequest request) {
        return userService.userDeactivation(id);
    }

    @Override
    public UserDTO userDetails(long id) {
        return userDetailsService.userDetails(id);
    }

    @Override
    public UserListDTO getUserListOfStatus(String stan) {
        return userListService.getUserListOfStatus(stan);
    }
}
