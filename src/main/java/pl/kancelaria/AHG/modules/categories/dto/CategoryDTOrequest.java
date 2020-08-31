package pl.kancelaria.AHG.modules.categories.dto;

import lombok.Data;
import pl.kancelaria.AHG.comon.model.resolutions.OrPublic;

/**
 * @author Michal
 * @created 31/08/2020
 */
@Data
public class CategoryDTOrequest {
    private long id;
    private OrPublic czyPubliczny;
    private  String rodzajKategorii;
}
