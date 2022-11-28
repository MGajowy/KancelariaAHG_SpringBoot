package pl.kancelaria.AHG.administration.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Calendar;


@Data
@Builder
public class EventLogDTO {
    private long id;
    private String czynnosc;
    private Calendar dataCzynnosci;
    private String uzytkownik;
}
