package pl.kancelaria.AHG.modules.categories.dto;

import lombok.Data;


@Data
public class CategoryDTOrequest {
    private long id;
    private Boolean czyPubliczny;
    private String rodzajKategorii;
}
