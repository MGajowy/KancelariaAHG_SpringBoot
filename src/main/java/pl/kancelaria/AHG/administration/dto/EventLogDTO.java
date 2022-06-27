package pl.kancelaria.AHG.administration.dto;

import lombok.Data;
import pl.kancelaria.AHG.comon.model.users.user.UserOB;
import pl.kancelaria.AHG.user.dto.UserDTO;

import java.util.Calendar;


@Data
public class EventLogDTO {
    private long id;
    private String czynnosc;
    private Calendar data_czynnosci;
    private String uzytkownik;
}
