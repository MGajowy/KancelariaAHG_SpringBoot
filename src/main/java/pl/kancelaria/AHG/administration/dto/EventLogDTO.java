package pl.kancelaria.AHG.administration.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventLogDTO {
    private long id;
    private String czynnosc;
    private String dataCzynnosci;
    private String uzytkownik;
}
