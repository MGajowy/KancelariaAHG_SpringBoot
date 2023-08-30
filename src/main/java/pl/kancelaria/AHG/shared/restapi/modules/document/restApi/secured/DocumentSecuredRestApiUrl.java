package pl.kancelaria.AHG.shared.restapi.modules.document.restApi.secured;

import static pl.kancelaria.AHG.shared.restapi.RestApiUrlStale.*;

public class DocumentSecuredRestApiUrl {

    public static final String PATH_DOCUMENT = DOCUMENT + REST_PATH_SECURED;
    public static final String DOCUMENT_LIST = "/documentList";
    public static final String DOCUMENT_DETAILS= "/documentDetails";
    public static final String DOWNLOAD_DOCUMENT = "/download";
    public static final String UPLOAD_DOCUMENT = "/upload";
    public static final String DELETE_DOCUMENT = "/delete";
    public static final String DOCUMENT_LIST_FOR_USER = "/documentListForUser";
}