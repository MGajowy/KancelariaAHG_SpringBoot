package pl.kancelaria.AHG.user.services;

import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.comon.model.users.user.repository.UserRepository;
import pl.kancelaria.AHG.user.dto.UserDTO;


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
