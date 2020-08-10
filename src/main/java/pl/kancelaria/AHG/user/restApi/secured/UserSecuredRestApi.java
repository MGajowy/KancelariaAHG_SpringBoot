package pl.kancelaria.AHG.user.restApi.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.user.dto.UserListDTO;
import pl.kancelaria.AHG.user.services.UserListService;

/**
 * @author Michal
 * @created 29/07/2020
 */
@RestController
public class UserSecuredRestApi implements pl.kancelaria.AHG.shared.restapi.users.restapi.secured.UserSecuredRestApi {

    private final UserListService userListService;

@Autowired
    public UserSecuredRestApi(UserListService userListService) {
        this.userListService = userListService;
    }

    @Override
    public UserListDTO pobierzListeUzytkownikowDto() {
        return userListService.pobierzListeUzytkownikow();
    }
}
