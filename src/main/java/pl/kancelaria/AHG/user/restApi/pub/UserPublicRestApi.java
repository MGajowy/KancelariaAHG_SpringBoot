package pl.kancelaria.AHG.user.restApi.pub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.user.dto.ResetPasswordDTO;
import pl.kancelaria.AHG.user.services.UserService;


@RestController
public class UserPublicRestApi implements pl.kancelaria.AHG.shared.restapi.users.restapi.pub.UserPublicRestApi {

    private final UserService userService;

    @Autowired
    public UserPublicRestApi(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Boolean checkToken(String token) {
        return userService.checkToken(token);
    }

    @Override
    public Boolean passwordReset(ResetPasswordDTO dto) {
        return userService.passwordReset(dto);
    }
}
