package pl.kancelaria.AHG.user.services;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.comon.model.users.user.UserOB;
import pl.kancelaria.AHG.comon.model.users.user.repository.UserRepository;
import pl.kancelaria.AHG.user.dto.UserDTO;

/**
 * @author Michal
 * @created 28/08/2020
 */
@Service
public class ModifyUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ModifyUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO modyfikujUzytkownika(long id, UserDTO userDTO) {
         UserOB userOB = userRepository.getOne(id);
         userOB.setUserName(userDTO.getUsername());
         userOB.setPassword(passwordEncoder.encode(userDTO.getPassword()));
         userOB.setImie(userDTO.getImie());
         userOB.setNazwisko(userDTO.getNazwisko());
         userOB.setPlec(userDTO.getPlec());
         userOB.setTelefon(userDTO.getTelefon());
         userOB.setEmail(userOB.getEmail());
        final UserOB updateUser = userRepository.save(userOB);
        BeanUtils.copyProperties(updateUser, userDTO);
        return userDTO;

    }
}
