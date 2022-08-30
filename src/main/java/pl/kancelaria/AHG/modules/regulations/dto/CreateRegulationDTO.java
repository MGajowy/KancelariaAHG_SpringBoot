package pl.kancelaria.AHG.modules.regulations.dto;

import lombok.Data;

@Data
public class CreateRegulationDTO {
    private long id;
    private String nazwa;
    private String tresc;
    private Boolean czyPubliczny;
    private Long kategoria;
}
