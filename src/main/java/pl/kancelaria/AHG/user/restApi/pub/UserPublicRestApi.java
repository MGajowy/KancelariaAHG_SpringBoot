package pl.kancelaria.AHG.user.restApi.pub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.user.dto.ResetPasswordDTO;
import pl.kancelaria.AHG.user.services.UserService;

/**
 * @author Michal
 * @created 29/07/2020
 */
@RestController
public class UserPublicRestApi implements pl.kancelaria.AHG.shared.restapi.users.restapi.pub.UserPublicRestApi {

    private final UserService userService;

    @Autowired
    public UserPublicRestApi(UserService userService) {

        this.userService = userService;
    }

    @Override
    public Boolean weryfikujToken(String token) {
        Boolean wynik = userService.checkToken(token);
        return wynik;
    }

    @Override
    public Boolean ResetHasla(ResetPasswordDTO dto) {
        Boolean wynik = userService.ResetHasla(dto);
        return wynik;
    }
}
