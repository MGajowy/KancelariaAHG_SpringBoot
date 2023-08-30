package pl.kancelaria.AHG.modules.document.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DocumentListDTO {
    List<DocumentDTO> documentList;
    Long totalRecords;
}