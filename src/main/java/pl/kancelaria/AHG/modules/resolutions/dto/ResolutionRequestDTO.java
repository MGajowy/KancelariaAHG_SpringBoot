package pl.kancelaria.AHG.modules.resolutions.dto;

import lombok.Data;

@Data
public class ResolutionRequestDTO {
    private String resolutionName;
    private String contents;
    private Boolean isPublic;
    private Long categoryId;
}
