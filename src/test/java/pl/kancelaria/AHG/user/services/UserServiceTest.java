package pl.kancelaria.AHG.user.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.kancelaria.AHG.administration.services.EventLogService;
import pl.kancelaria.AHG.common.entityModel.users.roles.repository.RolesRepository;
import pl.kancelaria.AHG.common.entityModel.users.user.UserSexEnum;
import pl.kancelaria.AHG.common.entityModel.users.user.repository.UserRepository;
import pl.kancelaria.AHG.user.dto.AddUserDTO;
import pl.kancelaria.AHG.user.role.RolesName;


import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
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
    void shouldCreateAccountAdmin() {

    }

    private AddUserDTO createUser() {
        AddUserDTO userDTO = new AddUserDTO();
        userDTO.setImie("Adam");
        userDTO.setNazwisko("Adamowicz");
        userDTO.setUsername("adam");
        userDTO.setTelefon("1111111111");
//        userDTO.setStan(UserStateEnum.NIEAKTYWNY);
        userDTO.setRola(RolesName.USER);
        userDTO.setEmail("m@hhh.pl");
        userDTO.setPlec(UserSexEnum.MEZCZYZNA);
        return userDTO;
    }

}