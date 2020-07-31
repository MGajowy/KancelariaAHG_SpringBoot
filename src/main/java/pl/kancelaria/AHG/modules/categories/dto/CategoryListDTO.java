package pl.kancelaria.AHG.modules.categories.dto;

import lombok.Data;
import org.springframework.stereotype.Component;
import pl.kancelaria.AHG.comon.model.resolutions.OrPublic;

import java.util.List;

/**
 * @author Michal
 * @created 30/07/2020
 */

 @Data
public class CategoryListDTO {
List<CategoryDTO> listaKategorii;
}
