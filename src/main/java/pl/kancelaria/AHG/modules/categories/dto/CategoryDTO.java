package pl.kancelaria.AHG.modules.categories.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private long id;
    private Boolean czyPubliczny;
    private String rodzajKategorii;

}
