package pl.kancelaria.AHG.modules.categories.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTOrequest {
    private long id;
    private Boolean isPublic;
    private String categoryName;
}
