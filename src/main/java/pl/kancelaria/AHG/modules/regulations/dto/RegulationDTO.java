package pl.kancelaria.AHG.modules.regulations.dto;

import lombok.Data;

@Data
public class RegulationDTO {
    private long id;
    private String nazwa;
    private String tresc;
    private Boolean czyPubliczny;
    private String nazwaKategorii;
    private String dateAdded;
}
