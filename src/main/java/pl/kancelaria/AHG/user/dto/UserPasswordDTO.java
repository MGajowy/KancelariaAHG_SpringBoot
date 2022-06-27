package pl.kancelaria.AHG.user.dto;

import lombok.Data;


@Data
public class UserPasswordDTO {
    private String token;
    private String password;
}
