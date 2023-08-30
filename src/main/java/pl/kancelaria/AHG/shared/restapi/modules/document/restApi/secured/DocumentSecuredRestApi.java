package pl.kancelaria.AHG.shared.restapi.modules.document.restApi.secured;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.kancelaria.AHG.modules.document.dto.DocumentListDTO;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import java.io.IOException;

@Path(value = DocumentSecuredRestApiUrl.PATH_DOCUMENT)
@RequestMapping(value = DocumentSecuredRestApiUrl.PATH_DOCUMENT)
public interface DocumentSecuredRestApi {

    @POST
    @PostMapping(DocumentSecuredRestApiUrl.UPLOAD_DOCUMENT)
    @Path(DocumentSecuredRestApiUrl.UPLOAD_DOCUMENT)
    ResponseEntity<HttpStatus> uploadMultipatrFiles(@RequestParam("file") MultipartFile multipartFile,
                                                    @RequestParam("userId") Long userId) throws IOException;
    @DELETE
    @DeleteMapping(DocumentSecuredRestApiUrl.DELETE_DOCUMENT + "/{id}")
    @Path(DocumentSecuredRestApiUrl.DELETE_DOCUMENT)
    ResponseEntity<HttpEntity> deleteDocument(@PathVariable("id") Long id);

    @GET
    @GetMapping(DocumentSecuredRestApiUrl.DOCUMENT_LIST_FOR_USER + "/{id}" + "/{pageNumber}" + "/{pageSize}")
    @Path(DocumentSecuredRestApiUrl.DOCUMENT_LIST_FOR_USER)
    DocumentListDTO getDocumentList(@QueryParam("term") String term,
                                    @QueryParam("status") String status,
                                    @PathVariable("id") Long id,
                                    @PathVariable("pageNumber") final Integer pageNumber,
                                    @PathVariable("pageSize") final Integer pageSize, HttpServletRequest request);
}