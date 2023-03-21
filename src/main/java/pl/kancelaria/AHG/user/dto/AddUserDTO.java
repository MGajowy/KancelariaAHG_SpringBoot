package pl.kancelaria.AHG.user.dto;

import lombok.Data;
import pl.kancelaria.AHG.common.entityModel.users.user.UserSexEnum;
import pl.kancelaria.AHG.common.entityModel.users.user.UserStateEnum;
import pl.kancelaria.AHG.user.role.RolesName;

@Data
public class AddUserDTO {

    private String name;
    private String surname;
    private String username;
    private UserStateEnum activationState;
    private String email;
    private String phoneNumber;
    private UserSexEnum sex;
    private RolesName rolesName;
    //private Byte[] zdjecie_profilowe;
    // private Boolean czy_zaakceptowano_regulamin;
}
