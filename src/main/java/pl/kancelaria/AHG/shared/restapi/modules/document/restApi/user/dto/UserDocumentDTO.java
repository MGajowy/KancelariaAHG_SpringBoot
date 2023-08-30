package pl.kancelaria.AHG.shared.restapi.modules.document.restApi.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDocumentDTO {
    private Long id;
    private String name;
}
