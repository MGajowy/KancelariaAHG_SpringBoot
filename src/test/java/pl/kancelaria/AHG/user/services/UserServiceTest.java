package pl.kancelaria.AHG.user.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.kancelaria.AHG.administration.configuration.jwt.config.JwtTokenUtil;
import pl.kancelaria.AHG.administration.services.EventLogService;
import pl.kancelaria.AHG.common.entityModel.users.roles.RolesOB;
import pl.kancelaria.AHG.common.entityModel.users.roles.repository.RolesRepository;
import pl.kancelaria.AHG.common.entityModel.users.token.TokenOB;
import pl.kancelaria.AHG.common.entityModel.users.token.repository.TokenRepository;
import pl.kancelaria.AHG.common.entityModel.users.user.UserOB;
import pl.kancelaria.AHG.common.entityModel.users.user.UserSexEnum;
import pl.kancelaria.AHG.common.entityModel.users.user.UserStateEnum;
import pl.kancelaria.AHG.common.entityModel.users.user.repository.UserRepository;
import pl.kancelaria.AHG.common.service.MailSenderService;
import pl.kancelaria.AHG.user.dto.AddUserDTO;
import pl.kancelaria.AHG.user.dto.LocationDTO;
import pl.kancelaria.AHG.user.role.RolesName;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    TokenRepository tokenRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    RolesRepository rolesRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    EventLogService eventLogService;
    @Mock
    MailSenderService mailSenderService;
    @Mock
    JwtTokenUtil jwtTokenUtil;

    @InjectMocks
    UserService userService;

    @Test
    void shouldCreateNewUser() {
        // given
        AddUserDTO userDTO = createUser();
        RolesOB rolesOB = new RolesOB();
        when(rolesRepository.findAllByRolesName(RolesName.USER)).thenReturn(rolesOB);

        // when
        ResponseEntity<HttpStatus> actual = userService.createNewUser(userDTO);

        // then
        assertThat(actual.getStatusCodeValue()).isEqualTo(201);
        assertThat(userDTO.getUsername()).isNotEmpty();
        assertThat(userDTO.getName()).isEqualTo("Adam");
        assertThat(userDTO.getSurname()).isEqualTo("Adamowicz");
    }

    @Test
    void shouldActivateUser() {
        // given
        UserOB userOB = createUserOB();
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setAppUrl("http://localhost/gajos");
        locationDTO.setId(1L);

        // when
        when(userRepository.getOne(locationDTO.getId())).thenReturn(userOB);

        boolean actual = userService.userActivation(locationDTO);
        // then
        assertThat(actual).isTrue();
        assertThat(userOB.getActivationState()).isEqualTo(UserStateEnum.AKTYWNY);
    }

    @Test
    void shouldDeactivateUser() {
        // given
        UserOB userOB = createUserOB();
        when(userRepository.getOne(1L)).thenReturn(userOB);

        // when
        boolean actual = userService.userDeactivation(1L);

        // then
        assertThat(actual).isTrue();
        assertThat(userOB.getActivationState()).isEqualTo(UserStateEnum.NIEAKTYWNY);
    }

    @Test
    void shouldVerifyToken() {
        // given
        UserOB userOB = createUserOB();
        TokenOB tokenOB = createTokenForUser(userOB);

        // when
        when(tokenRepository.findByToken("1234")).thenReturn(tokenOB);

        Boolean actual = userService.checkToken("1234");
        // then
        assertThat(actual).isTrue();
    }

    private AddUserDTO createUser() {
        AddUserDTO userDTO = new AddUserDTO();
        userDTO.setName("Adam");
        userDTO.setSurname("Adamowicz");
        userDTO.setUsername("adam");
        userDTO.setPhoneNumber("1111111111");
        userDTO.setRolesName(RolesName.USER);
        userDTO.setEmail("m@hhh.pl");
        userDTO.setSex(UserSexEnum.MEZCZYZNA);
        return userDTO;
    }

    private UserOB createUserOB() {
        UserOB userOB = new UserOB();
        RolesOB rolesOB = new RolesOB();
        rolesOB.setId(1L);
        rolesOB.setRolesName(RolesName.USER);
        List<RolesOB> roles = new ArrayList<>();
        roles.add(rolesOB);
        userOB.setId(1L);
        userOB.setName("Adam");
        userOB.setSurname("Adamowicz");
        userOB.setUserName("adam");
        userOB.setPhoneNumber("1111111111");
        userOB.setActivationState(UserStateEnum.AKTYWNY);
        userOB.setRolesOBSet(roles);
        userOB.setEmail("m@hhh.pl");
        userOB.setSex(UserSexEnum.MEZCZYZNA);
        return userOB;
    }

    private TokenOB createTokenForUser(UserOB userOB) {
        TokenOB tokenOB = new TokenOB();
        tokenOB.setId(1L);
        tokenOB.setToken("1234");
        tokenOB.setUser(userOB);
        return tokenOB;
    }

}