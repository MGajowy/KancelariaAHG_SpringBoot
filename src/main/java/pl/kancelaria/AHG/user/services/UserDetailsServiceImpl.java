package pl.kancelaria.AHG.user.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.comon.model.users.user.UserOB;
import pl.kancelaria.AHG.comon.model.users.user.repository.UserRepository;
import pl.kancelaria.AHG.user.dto.UserDTO;

import java.util.Optional;

/**
 * @author Michal
 * @created 31/08/2020
 */
//@Primary
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
@Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findAllByUserName(s);
    }


    public UserDTO szczegoly(long id) {
        UserOB userOB = userRepository.getOne(id);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userOB, userDTO);
        return userDTO;
    }
}
