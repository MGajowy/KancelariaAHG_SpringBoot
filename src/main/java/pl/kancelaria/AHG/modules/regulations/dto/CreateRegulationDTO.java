package pl.kancelaria.AHG.modules.regulations.dto;

import lombok.Data;

@Data
public class CreateRegulationDTO {
    private long id;
    private String regulationName;
    private String contents;
    private Boolean isPublic;
    private Long category;
}
