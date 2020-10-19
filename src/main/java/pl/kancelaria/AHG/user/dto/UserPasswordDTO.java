package pl.kancelaria.AHG.user.dto;

import lombok.Data;

/**
 * @author Michal
 * @created 11/10/2020
 */
@Data
public class UserPasswordDTO {
    private String token;
    private String password;
}
