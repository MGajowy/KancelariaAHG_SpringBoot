package pl.kancelaria.AHG.user.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.administration.services.EventLogService;
import pl.kancelaria.AHG.common.entityModel.administration.eventLog.EventLogConstants;
import pl.kancelaria.AHG.common.entityModel.users.roles.RolesOB;
import pl.kancelaria.AHG.common.entityModel.users.roles.repository.RolesRepository;
import pl.kancelaria.AHG.common.entityModel.users.user.UserOB;
import pl.kancelaria.AHG.common.entityModel.users.user.repository.UserRepository;
import pl.kancelaria.AHG.user.dto.UserDTO;
import pl.kancelaria.AHG.user.role.RolesName;

import java.util.ArrayList;
import java.util.List;


@Service
public class ModifyUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;
    private final EventLogService eventLogService;
    Logger logger = LoggerFactory.getLogger(ModifyUserService.class);


    public ModifyUserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RolesRepository rolesRepository, EventLogService eventLogService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
        this.eventLogService = eventLogService;
    }

    public UserDTO modifyUser(long id, UserDTO userDTO) {
        List<RolesName> rolesNames = userDTO.getRoles();
        List<RolesOB> role = new ArrayList<>();
        for (RolesName rola : rolesNames) {
            RolesOB rolesOB = rolesRepository.findAllByRolesName(rola);
            role.add(rolesOB);
        }
        UserOB userOB = userRepository.getOne(id);
        userOB.setUserName(userDTO.getUsername());
        if (userDTO.getPassword() != null)
        userOB.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userOB.setName(userDTO.getName());
        userOB.setSurname(userDTO.getSurname());
        userOB.setSex(userDTO.getSex());
        userOB.setPhoneNumber(userDTO.getPhoneNumber());
        userOB.setEmail(userDTO.getEmail());
        userOB.setRolesOBSet(role);

        List<UserOB> userRoles = new ArrayList<>();
        userRoles.add(userOB);
        RolesOB roles = new RolesOB();
        roles.setUserOBSet(userRoles);

        final UserOB updateUser = userRepository.save(userOB);
        BeanUtils.copyProperties(updateUser, userDTO);
        logger.info("Zmodyfikowano uzytkownika o emailu: " + userOB.getEmail());
        eventLogService.createLog(EventLogConstants.MODYFIAKCJA_UZYTKOWNIKA, userDTO.getUsername());
        return userDTO;
    }
}
