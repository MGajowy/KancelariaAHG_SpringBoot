package pl.kancelaria.AHG.modules.document.restApi.secured;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.kancelaria.AHG.modules.document.dto.DocumentListDTO;
import pl.kancelaria.AHG.modules.document.service.DocumentService;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
public class DocumentSecuredRestApi implements pl.kancelaria.AHG.shared.restapi.modules.document.restApi.secured.DocumentSecuredRestApi {

    private final DocumentService documentService;

    @Override
    public ResponseEntity<HttpStatus> uploadMultipatrFiles(MultipartFile[] multipartFile, Long userId) throws Exception {
        Boolean documentOB = documentService.saveFile(multipartFile, userId);
        return documentOB != null ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<HttpEntity> deleteDocument(Long id) {
        return documentService.deleteDocument(id);
    }

    @Override
    public DocumentListDTO getDocumentList(String term, String status, Long id, Integer pageNumber, Integer pageSize, HttpServletRequest request) {
        return documentService.getDocumentListForUser(term, status, id, pageNumber, pageSize);
    }
}