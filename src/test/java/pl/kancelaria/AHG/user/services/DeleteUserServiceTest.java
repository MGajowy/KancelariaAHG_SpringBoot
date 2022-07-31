package pl.kancelaria.AHG.user.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.kancelaria.AHG.administration.services.EventLogService;
import pl.kancelaria.AHG.common.entityModel.administration.eventLog.EventLogConstants;
import pl.kancelaria.AHG.common.entityModel.users.roles.RolesOB;
import pl.kancelaria.AHG.common.entityModel.users.user.UserOB;
import pl.kancelaria.AHG.common.entityModel.users.user.UserSexEnum;
import pl.kancelaria.AHG.common.entityModel.users.user.UserStateEnum;
import pl.kancelaria.AHG.common.entityModel.users.user.repository.UserRepository;
import pl.kancelaria.AHG.user.role.RolesName;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@ExtendWith(MockitoExtension.class)
class DeleteUserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private EventLogService eventLogService;
    @InjectMocks
    private DeleteUserService deleteUserService;

    @Test
    void shouldRemoveUser() {
        // given
        UserOB userOB = createUserOB();
        when(userRepository.getOne(1L)).thenReturn(userOB);
        given(eventLogService.dodajLog(EventLogConstants.USUNIECIE_UZYTKOWNIKA, userOB.getUsername())).willReturn(true);

        // when
        ResponseEntity<HttpStatus> httpStatusResponseEntity = deleteUserService.usunUzytkownika(1L);

        // then
        assertThat(httpStatusResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void shouldNotRemoveUser() {
        // given
        UserOB userOB = createUserOB();

        // when
        ResponseEntity<HttpStatus> httpStatusResponseEntity = deleteUserService.usunUzytkownika(null);

        // then
        assertThat(httpStatusResponseEntity.getStatusCode()).isEqualTo(HttpStatus.EXPECTATION_FAILED);
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