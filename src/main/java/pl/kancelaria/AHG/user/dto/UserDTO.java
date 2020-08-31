package pl.kancelaria.AHG.user.dto;

import lombok.Data;
import pl.kancelaria.AHG.comon.model.users.user.UserAccountType;
import pl.kancelaria.AHG.comon.model.users.user.UserSexEnum;
import pl.kancelaria.AHG.comon.model.users.user.UserStateEnum;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author Michal
 * @created 27/07/2020
 */
@Data
public class UserDTO {
    private String imie;
    private String nazwisko;
    private String username;
    private  String password;
    private UserStateEnum stan;
    //private UserAccountType typ_konta;
    private String email;
    //private Boolean czy_zaakceptowano_regulamin;
    private String telefon;
    private UserSexEnum plec;
    //private Byte[] zdjecie_profilowe;
    //private LocalDate dataRejestracji;
}
