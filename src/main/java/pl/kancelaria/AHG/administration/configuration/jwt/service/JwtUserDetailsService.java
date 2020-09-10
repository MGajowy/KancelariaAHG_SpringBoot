package pl.kancelaria.AHG.administration.configuration.jwt.service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pl.kancelaria.AHG.comon.model.users.user.UserOB;
import pl.kancelaria.AHG.comon.model.users.user.UserStateEnum;
import pl.kancelaria.AHG.comon.model.users.user.repository.UserRepository;
import pl.kancelaria.AHG.user.dto.RegistrationDTO;

/**
 * @author Michal
 * @created 01/09/2020
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  PasswordEncoder bcryptEncoder;
//    @Autowired
//    public JwtUserDetailsService(UserRepository userRepository, PasswordEncoder bcryptEncoder) {
//        this.userRepository = userRepository;
//        this.bcryptEncoder = bcryptEncoder;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserOB user = userRepository.findAllByUserName(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public UserOB save(RegistrationDTO user) {
        UserOB newUser = new UserOB();
        newUser.setImie(user.getImie());
        newUser.setNazwisko(user.getNazwisko());
        newUser.setStan(UserStateEnum.NIEAKTYWNY);
        //newUser.setStan(user.getStan());
        newUser.setUserName(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setTelefon(user.getTelefon());
        newUser.setPlec(user.getPlec());
        this.userRepository.save(newUser);
        return newUser;
    }
}
