package pl.kancelaria.AHG.user.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

//@MockitoSettings(strictness = Strictness.STRICT_STUBS)
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
        given(eventLogService.createLog(EventLogConstants.USUNIECIE_UZYTKOWNIKA, userOB.getUsername())).willReturn(true);

        // when
        ResponseEntity<HttpStatus> httpStatusResponseEntity = deleteUserService.deleteUser(1L);

        // then
        assertThat(httpStatusResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldNotRemoveUser() {
        // given
        UserOB userOB = createUserOB();

        // when
        ResponseEntity<HttpStatus> httpStatusResponseEntity = deleteUserService.deleteUser(null);

        // then
        assertThat(httpStatusResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    private UserOB createUserOB() {
        UserOB userOB = new UserOB();
        RolesOB rolesOB = new RolesOB();
        rolesOB.setId(1);
        rolesOB.setRolesName(RolesName.USER);
        List<RolesOB> roles = new ArrayList<>();
        roles.add(rolesOB);
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
}