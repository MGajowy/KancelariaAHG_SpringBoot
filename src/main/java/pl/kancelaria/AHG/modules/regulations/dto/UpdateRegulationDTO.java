package pl.kancelaria.AHG.modules.regulations.dto;

import lombok.Data;

@Data
public class UpdateRegulationDTO {
    private String regulationName;
    private String contents;
    private Boolean isPublic;
    private Long categoryId;
}
