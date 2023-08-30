package pl.kancelaria.AHG.shared.restapi.modules.document.restApi.pub;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kancelaria.AHG.modules.document.dto.DocumentListDTO;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path(value = DocumentPublicRestApiUrl.PATH_DOCUMENT)
@RequestMapping(value = DocumentPublicRestApiUrl.PATH_DOCUMENT)
public interface DocumentPublicRestApi {

    @GET
    @GetMapping(DocumentPublicRestApiUrl.DOCUMENT_LIST + "/{pageNumber}" + "/{pageSize}")
    @Path(DocumentPublicRestApiUrl.DOCUMENT_LIST)
    DocumentListDTO getDocumentList(@QueryParam("term") String term,
                                    @PathVariable("pageNumber") final Integer pageNumber,
                                    @PathVariable("pageSize") final Integer pageSize, HttpServletRequest request);

    @GET
    @GetMapping(DocumentPublicRestApiUrl.DOWNLOAD_DOCUMENT + "/{id}")
    @Path(DocumentPublicRestApiUrl.DOWNLOAD_DOCUMENT)
    ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("id") Long id);
}