package pl.kancelaria.AHG.modules.categories.dto;

import lombok.Data;
import pl.kancelaria.AHG.comon.model.resolutions.OrPublic;

/**
 * @author Michal
 * @created 31/07/2020
 */
@Data
public class CategoryDTO {
    private OrPublic czyPubliczny;
    private  String rodzajKategorii;
}
