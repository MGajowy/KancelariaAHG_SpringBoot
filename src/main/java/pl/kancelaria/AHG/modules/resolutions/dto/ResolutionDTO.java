package pl.kancelaria.AHG.modules.resolutions.dto;

import lombok.Builder;
import lombok.Data;
import pl.kancelaria.AHG.comon.model.resolutions.OrPublic;

/**
 * @author Michal
 * @created 09/01/2021
 */
@Data
public class ResolutionDTO {
    private long id;
    private String opis;
    private String tresc;
    private Boolean czyPubliczny;
    private String nazwaKategorii;
}
