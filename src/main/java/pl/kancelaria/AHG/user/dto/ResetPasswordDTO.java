package pl.kancelaria.AHG.user.dto;

import lombok.Data;


@Data
public class ResetPasswordDTO {
    private String username;
    private String appUrl;
}
