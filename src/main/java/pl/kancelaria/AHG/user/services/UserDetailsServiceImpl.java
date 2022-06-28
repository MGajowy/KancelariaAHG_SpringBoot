package pl.kancelaria.AHG.user.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.common.entityModel.users.roles.RolesOB;
import pl.kancelaria.AHG.common.entityModel.users.roles.repository.RolesRepository;
import pl.kancelaria.AHG.common.entityModel.users.user.UserOB;
import pl.kancelaria.AHG.common.entityModel.users.user.repository.UserRepository;
import pl.kancelaria.AHG.user.dto.UserDTO;
import pl.kancelaria.AHG.user.role.RolesName;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;
@Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.rolesRepository = rolesRepository;
}

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findAllByUserName(s);
    }

    public UserDTO szczegoly(long id) {
        UserOB userOB = userRepository.getOne(id);
        List<RolesOB> rolesNames = userOB.getRolesOBSet();
        UserDTO userDTO = new UserDTO();
        List<RolesName> rolesNameList = new ArrayList<>();
        for (RolesOB rola: rolesNames) {
           RolesName nowaRola =  rola.getNazwa();
            rolesNameList.add(nowaRola);
        }
        userDTO.setRole(rolesNameList);
        BeanUtils.copyProperties(userOB, userDTO);
        return userDTO;
    }
}
