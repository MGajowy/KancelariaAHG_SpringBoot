package pl.kancelaria.AHG.user.services;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kancelaria.AHG.common.entityModel.users.roles.RolesOB;
import pl.kancelaria.AHG.common.entityModel.users.user.UserOB;
import pl.kancelaria.AHG.common.entityModel.users.user.UserSexEnum;
import pl.kancelaria.AHG.common.entityModel.users.user.UserStateEnum;
import pl.kancelaria.AHG.user.dto.UserDTO;
import pl.kancelaria.AHG.user.dto.UserListDTO;
import pl.kancelaria.AHG.user.role.RolesName;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
class UserListServiceTest {
    private final static String TERM = "admin";

    @Mock
    EntityManager entityManager;

    @InjectMocks
    private UserListService userListService;

    @Test
    @Disabled
    public void shouldReturnUsersList() {
        // given
        List<UserDTO> listaUzytkownikow = new ArrayList<>();
        listaUzytkownikow.add(createUser());
        // when

        UserListDTO userListDTO = userListService.getUserList("adam");

        // then
        assertThat(userListDTO.getUsersList().get(0).getUsername()).isEqualTo("adam");
    }

    private UserDTO createUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Adam");
        userDTO.setSurname("Adamowicz");
        userDTO.setUsername("adam");
        userDTO.setPhoneNumber("1111111111");
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

}