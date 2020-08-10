package pl.kancelaria.AHG.user.services;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.kancelaria.AHG.comon.model.users.user.UserOB;
import pl.kancelaria.AHG.comon.model.users.user.repository.UserRepository;
import pl.kancelaria.AHG.user.dto.UserDTO;
import pl.kancelaria.AHG.user.dto.UserListDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal
 * @created 09/08/2020
 */
@Service
public class UserListService {

    public final UserRepository userRepository;

    public UserListService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserListDTO pobierzListeUzytkownikow(){
        UserListDTO response = new UserListDTO();
        List<UserOB> userOBList = this.userRepository.findAll();
        if (!CollectionUtils.isEmpty(userOBList)) {
            List<UserDTO> uzytkownik = new ArrayList<>();
            userOBList.forEach(u -> {UserDTO daneDTO = new UserDTO();
                BeanUtils.copyProperties(u, daneDTO);
                uzytkownik.add(daneDTO);
            });
            response.setListaUzytkownikow(uzytkownik);
        }
        else{
            response.setListaUzytkownikow(new ArrayList<>());
        }

        return response;
    }
}
