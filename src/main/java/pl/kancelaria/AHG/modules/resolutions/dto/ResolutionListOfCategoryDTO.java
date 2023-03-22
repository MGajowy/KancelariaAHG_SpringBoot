package pl.kancelaria.AHG.modules.resolutions.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResolutionListOfCategoryDTO {
    String categoryName;
    List<ResolutionDTO> resolutionsList;
}
