package pl.kancelaria.AHG.shared.restapi.modules.regulations.restApi.pub;

import static pl.kancelaria.AHG.shared.restapi.RestApiUrlStale.REST_PATH_PUBLIC;
import static pl.kancelaria.AHG.shared.restapi.RestApiUrlStale.ROZPORZADZENIA;

public class RegulationPublicRestApiUrl {

    public static final String SCIEZKA_ROZPORZADZENIA = ROZPORZADZENIA + REST_PATH_PUBLIC;
    public static final String ROZPORZADZENIA_LISTA = "/listaRozporzadzen";
    public static final String ROZPORZADZENIA_LISTA_PO_KATEGORII = "/listaRozporzadzenKategoria";
    public static final String ROZPORZADZENIA_LISTA_PO_OPISIE = "/listaRozporzadzenWgOpis";
}
