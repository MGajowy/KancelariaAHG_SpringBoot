package pl.kancelaria.AHG.user.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.comon.model.users.user.repository.UserRepository;

/**
 * @author Michal
 * @created 28/08/2020
 */
@Service
public class DeleteUserService {
    private final UserRepository userRepository;

    public DeleteUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public ResponseEntity<HttpStatus> usunUzytkownika (long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
