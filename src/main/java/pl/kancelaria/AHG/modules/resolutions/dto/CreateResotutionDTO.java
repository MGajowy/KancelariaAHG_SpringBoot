package pl.kancelaria.AHG.modules.resolutions.dto;

import lombok.Data;
import pl.kancelaria.AHG.comon.model.resolutions.OrPublic;

/**
 * @author Michal
 * @created 09/01/2021
 */
@Data
public class CreateResotutionDTO {
    private long id;
    private String opis;
    private String tresc;
    private Boolean czyPubliczny;
    private Long kategoria;
}
