package pl.kancelaria.AHG.user.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.comon.model.users.user.UserOB;
import pl.kancelaria.AHG.comon.model.users.user.UserSexEnum;
import pl.kancelaria.AHG.comon.model.users.user.UserStateEnum;
import pl.kancelaria.AHG.comon.model.users.user.repository.UserRepository;
import pl.kancelaria.AHG.user.dto.UserDTO;

/**
 * @author Michal
 * @created 20/08/2020
 */
@Service
public class AddUserService {

    private UserRepository userRepository;

    public AddUserService( UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public UserDTO utworzUzytkownikaZadministracji(){
        return null;
    }
}
