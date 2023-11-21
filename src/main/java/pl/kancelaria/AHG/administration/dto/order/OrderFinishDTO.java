package pl.kancelaria.AHG.administration.dto.order;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderFinishDTO {
    private long id;
    private LocalDate dateToClose;
}
