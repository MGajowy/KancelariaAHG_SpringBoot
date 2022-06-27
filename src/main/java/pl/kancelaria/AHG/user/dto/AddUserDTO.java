package pl.kancelaria.AHG.user.dto;

import lombok.Data;
import pl.kancelaria.AHG.comon.model.users.user.UserSexEnum;
import pl.kancelaria.AHG.comon.model.users.user.UserStateEnum;
import pl.kancelaria.AHG.user.role.RolesName;


@Data
public class AddUserDTO {
    private String imie;
    private String nazwisko;
    private String username;
    //private  String password;
    private UserStateEnum stan;
    //private UserAccountType typ_konta;
    private String email;
    //private Boolean czy_zaakceptowano_regulamin;
    private String telefon;
    private UserSexEnum plec;
    private RolesName rola;
    //private Byte[] zdjecie_profilowe;
    //private LocalDate dataRejestracji;
}
