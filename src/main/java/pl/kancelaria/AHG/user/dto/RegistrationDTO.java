package pl.kancelaria.AHG.user.dto;

import lombok.Data;
import pl.kancelaria.AHG.common.entityModel.users.user.UserSexEnum;
import pl.kancelaria.AHG.common.entityModel.users.user.UserStateEnum;


@Data
public class RegistrationDTO {

        private String name;
        private String surname;
        private String username;
        private  String password;
        private UserStateEnum activationState;
        private String email;
        private String phoneNumber;
        private UserSexEnum sex;
        //private Byte[] zdjecie_profilowe;

}

