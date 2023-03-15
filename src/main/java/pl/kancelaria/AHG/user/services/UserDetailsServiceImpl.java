package pl.kancelaria.AHG.user.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.common.entityModel.users.roles.RolesOB;
import pl.kancelaria.AHG.common.entityModel.users.roles.repository.RolesRepository;
import pl.kancelaria.AHG.common.entityModel.users.user.UserOB;
import pl.kancelaria.AHG.common.entityModel.users.user.repository.UserRepository;
import pl.kancelaria.AHG.common.service.DateConvertService;
import pl.kancelaria.AHG.user.dto.UserDTO;
import pl.kancelaria.AHG.user.role.RolesName;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;
    private final DateConvertService dateConvertService;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findAllByUserName(s);
    }

    public UserDTO userDetails(long id) {
        UserOB userOB = userRepository.getOne(id);
        List<RolesOB> rolesNames = userOB.getRolesOBSet();
        UserDTO userDTO = new UserDTO();
        List<RolesName> rolesNameList = new ArrayList<>();
        for (RolesOB rola : rolesNames) {
            RolesName nowaRola = rola.getNazwa();
            rolesNameList.add(nowaRola);
        }
        userDTO.setRole(rolesNameList);
        BeanUtils.copyProperties(userOB, userDTO);
        userDTO.setDateAdded(dateConvertService.convertDateToString(userOB.getDateAdded()));
        return userDTO;
    }
}
