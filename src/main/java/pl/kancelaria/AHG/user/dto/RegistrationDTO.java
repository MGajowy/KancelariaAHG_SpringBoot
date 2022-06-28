package pl.kancelaria.AHG.user.dto;

import lombok.Data;
import pl.kancelaria.AHG.common.entityModel.users.user.UserSexEnum;
import pl.kancelaria.AHG.common.entityModel.users.user.UserStateEnum;


@Data
public class RegistrationDTO {

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

