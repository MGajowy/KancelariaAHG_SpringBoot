package pl.kancelaria.AHG.modules.resolutions.dto;

import lombok.Data;


@Data
public class ResolutionDTO {
    private long id;
    private String opis;
    private String tresc;
    private Boolean czyPubliczny;
    private String nazwaKategorii;
}
