package pl.kancelaria.AHG.user.dto;

import lombok.Data;
import pl.kancelaria.AHG.comon.model.users.user.UserAccountType;
import pl.kancelaria.AHG.comon.model.users.user.UserStateEnum;

/**
 * @author Michal
 * @created 27/07/2020
 */
@Data
public class UserDTO {
    private String imie;
    private String nazwisko;
   // private String login;
    private UserAccountType typ_konta;
    private UserStateEnum stan;
  //  private String email;

}
