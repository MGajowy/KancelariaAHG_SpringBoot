package pl.kancelaria.AHG.user.dto;

import lombok.Data;
import pl.kancelaria.AHG.common.entityModel.users.user.UserSexEnum;
import pl.kancelaria.AHG.common.entityModel.users.user.UserStateEnum;
import pl.kancelaria.AHG.user.role.RolesName;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private UserStateEnum activationState;
    private String email;
    private String phoneNumber;
    private UserSexEnum sex;
    private List<RolesName> roles;
    private String dateAdded;
    //private UserAccountType typ_konta;
    //private Boolean czy_zaakceptowano_regulamin;
    //private Byte[] zdjecie_profilowe;
}
