package pl.kancelaria.AHG.modules.categories.dto;

import lombok.Data;


@Data
public class CategoryDTO {

    private long id;
    private Boolean czyPubliczny;
    private  String rodzajKategorii;
}
