package pl.kancelaria.AHG.modules.document.restApi.pub;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.modules.document.dto.DocumentDTO;
import pl.kancelaria.AHG.modules.document.dto.DocumentListDTO;
import pl.kancelaria.AHG.modules.document.service.DocumentService;

import javax.servlet.http.HttpServletRequest;


@RestController
@AllArgsConstructor
public class DocumentPublicRestApi implements pl.kancelaria.AHG.shared.restapi.modules.document.restApi.pub.DocumentPublicRestApi {

    private final DocumentService documentService;

    @Override
    public DocumentListDTO getDocumentList(String term, Integer pageNumber, Integer pageSize, HttpServletRequest request) {
        return documentService.getDocumentList(term, pageNumber, pageSize, request);
    }

    @Override
    public ResponseEntity<ByteArrayResource> downloadFile(Long fileId) {
        DocumentDTO documentDTO = documentService.downloadFile(fileId);
        if (documentDTO != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(documentDTO.getDocType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + documentDTO.getDocName() + "\"")
                    .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
                    .body(new ByteArrayResource(documentDTO.getData()));
        }
        return ResponseEntity.noContent().build();
    }
}