package pl.kancelaria.AHG.user.dto;

import lombok.Data;
import pl.kancelaria.AHG.common.entityModel.users.user.UserSexEnum;
import pl.kancelaria.AHG.common.entityModel.users.user.UserStateEnum;
import pl.kancelaria.AHG.user.role.RolesName;

import java.util.List;


@Data
public class UserDTO {
    private Long id;
    private String imie;
    private String nazwisko;
    private String username;
    private String password;
    private UserStateEnum stan;
    private String email;
    private String telefon;
    private UserSexEnum plec;
    private List<RolesName> role;
    private String dateAdded;
    //private UserAccountType typ_konta;
    //private Boolean czy_zaakceptowano_regulamin;
    //private Byte[] zdjecie_profilowe;
}
