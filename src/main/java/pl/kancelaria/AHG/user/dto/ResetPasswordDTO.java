package pl.kancelaria.AHG.user.dto;

import lombok.Data;

/**
 * @author Michal
 * @created 20/10/2020
 */
@Data
public class ResetPasswordDTO {
    private String username;
    private String appUrl;
}
