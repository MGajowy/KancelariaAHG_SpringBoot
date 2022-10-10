package pl.kancelaria.AHG.modules.about.onlineHelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineHelpRequestDto {
    private String name;
    private String email;
    private String message;
    private String phoneNumber;
}
