package pl.kancelaria.AHG.shared.restapi.modules.resolutions.restapi.pub;

import static pl.kancelaria.AHG.shared.restapi.RestApiUrlStale.UCHWALY;
import static pl.kancelaria.AHG.shared.restapi.RestApiUrlStale.REST_PATH_PUBLIC;

public class ResolutionPublicRestApiUrl {
    public static final String SCIEZKA_UCHWALY = UCHWALY + REST_PATH_PUBLIC;
    public static final String UCHWALY_LISTA = "/listaUchwal";

    public static final String UCHWALY_LISTA_PO_KATEGORII = "/listaUchwalKategoria" ;
}
