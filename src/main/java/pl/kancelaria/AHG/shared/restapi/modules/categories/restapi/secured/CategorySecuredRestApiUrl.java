package pl.kancelaria.AHG.shared.restapi.modules.categories.restapi.secured;

import static pl.kancelaria.AHG.shared.restapi.RestApiUrlStale.*;

/**
 * @author Michal
 * @created 09/08/2020
 */
public class CategorySecuredRestApiUrl {
    public static final String SCIEZKA_KATEGORIE = KATEGORIE + REST_PATH_SECURED;

    public static final String DODAJ_KATEGORIE = "/dodaj-kategorie";
    public static final String MODYFIKUJ_KATEGORIE = "/modyfikuj-kategorie";
    public static final String USUN_KATEGORIE = "/usun-kategorie";

}
