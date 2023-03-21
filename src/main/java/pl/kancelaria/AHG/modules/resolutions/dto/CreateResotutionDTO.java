package pl.kancelaria.AHG.modules.resolutions.dto;

import lombok.Data;

@Data
public class CreateResotutionDTO {
    private long id;
    private String resolutionName;
    private String contents;
    private Boolean isPublic;
    private Long categoryId;
}
