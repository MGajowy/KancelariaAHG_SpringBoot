package pl.kancelaria.AHG.administration.configuration.jwt.service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.administration.configuration.jwt.config.JwtTokenUtil;
import pl.kancelaria.AHG.comon.model.users.roles.RolesOB;
import pl.kancelaria.AHG.comon.model.users.roles.repository.RolesRepository;
import pl.kancelaria.AHG.comon.model.users.user.UserOB;
import pl.kancelaria.AHG.comon.model.users.user.UserStateEnum;
import pl.kancelaria.AHG.comon.model.users.user.repository.UserRepository;
import pl.kancelaria.AHG.user.dto.RegistrationDTO;
import pl.kancelaria.AHG.user.role.RolesName;


@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private  PasswordEncoder bcryptEncoder;

    public JwtUserDetailsService() {
    }

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
        RolesOB role=  rolesRepository.findAllByNazwa(RolesName.USER);
        List<RolesOB> lista = new ArrayList<>();
        lista.add(role);
        UserOB newUser = new UserOB();
        newUser.setImie(user.getImie());
        newUser.setNazwisko(user.getNazwisko());
        newUser.setStan(UserStateEnum.NIEAKTYWNY);
        newUser.setUserName(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setTelefon(user.getTelefon());
        newUser.setPlec(user.getPlec());
        newUser.setRolesOBSet(lista);
        List<UserOB> userRoles = new ArrayList<>();
        userRoles.add(newUser);
        role.setUserOBSet(userRoles);
        this.userRepository.save(newUser);
        return newUser;
    }
}
