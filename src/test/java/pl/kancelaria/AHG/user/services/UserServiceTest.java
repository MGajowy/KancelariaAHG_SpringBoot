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
import pl.kancelaria.AHG.user.dto.AddUserDTO;
import pl.kancelaria.AHG.user.role.RolesName;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    RolesRepository rolesRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    EventLogService eventLogService;
    @InjectMocks
    UserService userService;

    @Test
    void shouldCreateNewUser() {
        // given
        AddUserDTO userDTO = createUser();
        RolesOB rolesOB = new RolesOB();
        when(rolesRepository.findAllByNazwa(RolesName.USER)).thenReturn(rolesOB);

        // when
        boolean actual = userService.utworzNowegoUzytkownika(createUser());

        // then
        assertThat(actual).isTrue();
    }

    @Test
    void shouldDeactivateUser() {
        // given
        UserOB userOB = createUserOB();
        when(userRepository.getOne(1L)).thenReturn(userOB);

        // when
        boolean actual = userService.dezaktuwujUzytkownika(1L);

        // then
        assertThat(actual).isTrue();
    }

    private AddUserDTO createUser() {
        AddUserDTO userDTO = new AddUserDTO();
        userDTO.setImie("Adam");
        userDTO.setNazwisko("Adamowicz");
        userDTO.setUsername("adam");
        userDTO.setTelefon("1111111111");
//        userDTO.setStan(UserStateEnum.AKTYWNY);
        userDTO.setRola(RolesName.USER);
        userDTO.setEmail("m@hhh.pl");
        userDTO.setPlec(UserSexEnum.MEZCZYZNA);
        return userDTO;
    }

    private UserOB createUserOB() {
        UserOB userOB = new UserOB();
        RolesOB rolesOB = new RolesOB();
        rolesOB.setId(1);
        rolesOB.setNazwa(RolesName.USER);
        List<RolesOB> roles = new ArrayList<>();
        roles.add(rolesOB);
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

}