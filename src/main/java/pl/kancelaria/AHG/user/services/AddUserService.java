package pl.kancelaria.AHG.user.services;

import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.common.entityModel.users.user.repository.UserRepository;
import pl.kancelaria.AHG.user.dto.UserDTO;


@Service
public class AddUserService {

    private UserRepository userRepository;

    public AddUserService( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //todo metoda do dokończenia lub usuniecia
    public UserDTO utworzUzytkownikaZadministracji(){
        return null;
    }
}
