package pl.kancelaria.AHG.user.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.kancelaria.AHG.administration.configuration.jwt.config.JwtTokenUtil;
import pl.kancelaria.AHG.administration.services.EventLogService;
import pl.kancelaria.AHG.comon.model.users.roles.RolesOB;
import pl.kancelaria.AHG.comon.model.users.roles.repository.RolesRepository;
import pl.kancelaria.AHG.comon.model.users.token.repository.TokenRepository;
import pl.kancelaria.AHG.comon.model.users.user.UserOB;
import pl.kancelaria.AHG.comon.model.users.user.UserSexEnum;
import pl.kancelaria.AHG.comon.model.users.user.UserStateEnum;
import pl.kancelaria.AHG.comon.model.users.user.repository.UserRepository;
import pl.kancelaria.AHG.comon.service.MailSenderService;
import pl.kancelaria.AHG.user.dto.AddUserDTO;
import pl.kancelaria.AHG.user.role.RolesName;

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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