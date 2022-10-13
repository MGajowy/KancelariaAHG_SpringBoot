package pl.kancelaria.AHG.user.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.kancelaria.AHG.administration.services.EventLogService;
import pl.kancelaria.AHG.common.entityModel.users.roles.RolesOB;
import pl.kancelaria.AHG.common.entityModel.users.roles.repository.RolesRepository;
import pl.kancelaria.AHG.common.entityModel.users.user.UserOB;
import pl.kancelaria.AHG.common.entityModel.users.user.UserSexEnum;
import pl.kancelaria.AHG.common.entityModel.users.user.UserStateEnum;
import pl.kancelaria.AHG.common.entityModel.users.user.repository.UserRepository;
import pl.kancelaria.AHG.user.dto.UserDTO;
import pl.kancelaria.AHG.user.role.RolesName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ModifyUserServiceTest {

    private final static String IMIE = "Piotr";
    private final static String USERNAME = "Kornel";

    @Mock
    UserRepository userRepository;
    @Mock
    RolesRepository rolesRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    EventLogService eventLogService;
    @InjectMocks
    ModifyUserService modifyUserService;
    @Test
    void shouldModifyUser() {
        // given
        UserDTO modifyUser = createModifyUserDTO();
        UserOB userOB = createUserOB();
        UserOB modifyUserOB = modifyUserOB();
        when(userRepository.getOne(1L)).thenReturn(userOB);
        when(userRepository.save(any())).thenReturn(modifyUserOB);

        // when
        UserDTO userDTO = modifyUserService.modifyUser(1L, modifyUser);

        // then
        assertThat(userDTO.getUsername()).isEqualTo(USERNAME);
        assertThat(userDTO).isNotNull();
        assertThat(userDTO.getImie()).isEqualTo(IMIE);

    }

    private UserDTO createModifyUserDTO() {
        UserDTO userDTO = new UserDTO();
        List<RolesName> rolesNames = Arrays.asList(RolesName.USER);
        userDTO.setUsername("Kornel");
        userDTO.setTelefon("123456789");
        userDTO.setPlec(UserSexEnum.MEZCZYZNA);
        userDTO.setNazwisko("Gajowy");
        userDTO.setImie("Piotr");
        userDTO.setStan(UserStateEnum.AKTYWNY);
        userDTO.setRole(rolesNames);
        userDTO.setPassword("adsad");
        return userDTO;
    }

    private UserOB createUserOB() {
        UserOB userOB = new UserOB();
        RolesOB rolesOB = new RolesOB();
        rolesOB.setId(1L);
        rolesOB.setNazwa(RolesName.USER);
        List<RolesOB> roles = new ArrayList<>();
        roles.add(rolesOB);
        userOB.setId(1L);
        userOB.setImie("Adam");
        userOB.setNazwisko("Adamowicz");
        userOB.setUserName("adam");
        userOB.setTelefon("1111111111");
        userOB.setStan(UserStateEnum.AKTYWNY);
        userOB.setRolesOBSet(roles);
        userOB.setEmail("m@hhh.pl");
        userOB.setPlec(UserSexEnum.MEZCZYZNA);
        return userOB;
    }
    private UserOB modifyUserOB() {
        UserOB userOB = new UserOB();
        RolesOB rolesOB = new RolesOB();
        rolesOB.setId(1L);
        rolesOB.setNazwa(RolesName.USER);
        List<RolesOB> roles = new ArrayList<>();
        roles.add(rolesOB);
        userOB.setId(1L);
        userOB.setImie("Piotr");
        userOB.setNazwisko("Gajowy");
        userOB.setUserName("Kornel");
        userOB.setTelefon("123456789");
        userOB.setStan(UserStateEnum.AKTYWNY);
        userOB.setRolesOBSet(roles);
        userOB.setEmail("m@hhh.pl");
        userOB.setPlec(UserSexEnum.MEZCZYZNA);
        return userOB;
    }
}