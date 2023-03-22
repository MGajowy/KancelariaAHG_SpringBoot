package pl.kancelaria.AHG.modules.regulations.dto;

import lombok.Data;

@Data
public class RegulationDTO {
    private long id;
    private String regulationName;
    private String contents;
    private Boolean isPublic;
    private String categoryName;
    private String dateAdded;
}
