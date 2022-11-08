package pl.kancelaria.AHG.modules.resolutions.dto;

import lombok.Data;

@Data
public class ResolutionRequestDTO {
    private String opis;
    private String tresc;
    private Boolean czyPubliczny;
    private Long idKategorii;
}
