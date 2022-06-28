package pl.kancelaria.AHG.administration.dto;

import lombok.Data;

import java.util.Calendar;


@Data
public class EventLogDTO {
    private long id;
    private String czynnosc;
    private Calendar data_czynnosci;
    private String uzytkownik;
}
