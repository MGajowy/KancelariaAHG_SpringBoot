package pl.kancelaria.AHG.modules.document.dto;
import lombok.Builder;
import lombok.Data;
import pl.kancelaria.AHG.common.entityModel.document.StatusFile;

import java.time.LocalDate;

@Data
@Builder
public class DocumentDTO {
    private long id;
    private String docName;
    private String docType;
    private byte[] data;
    private LocalDate createDate;
    private LocalDate deleteDate;
    private StatusFile statusFile;
    private Long userID;
}