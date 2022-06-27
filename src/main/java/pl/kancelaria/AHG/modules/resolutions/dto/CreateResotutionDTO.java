package pl.kancelaria.AHG.modules.resolutions.dto;

import lombok.Data;

@Data
public class CreateResotutionDTO {
    private long id;
    private String opis;
    private String tresc;
    private Boolean czyPubliczny;
    private Long kategoria;
}
