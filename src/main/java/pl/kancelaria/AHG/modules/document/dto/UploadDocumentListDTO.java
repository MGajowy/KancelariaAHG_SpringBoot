package pl.kancelaria.AHG.modules.document.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UploadDocumentListDTO {
    List<MultipartFile> fileList;
    Long userId;
}