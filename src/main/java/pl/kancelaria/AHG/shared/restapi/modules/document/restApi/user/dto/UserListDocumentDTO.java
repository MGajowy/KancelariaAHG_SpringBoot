package pl.kancelaria.AHG.shared.restapi.modules.document.restApi.user.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserListDocumentDTO {
    List<UserDocumentDTO> userDocument;
}
