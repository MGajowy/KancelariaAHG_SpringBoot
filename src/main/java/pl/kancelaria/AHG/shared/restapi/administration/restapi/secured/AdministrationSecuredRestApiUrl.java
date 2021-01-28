package pl.kancelaria.AHG.shared.restapi.administration.restapi.secured;

import static pl.kancelaria.AHG.shared.restapi.RestApiUrlStale.*;

/**
 * @author Michal
 * @created 29/07/2020
 */
public class AdministrationSecuredRestApiUrl {
    //todo sprawdzic sciezke czy jest obsluzona.
    public static final String USUN_UZYTKOWNIKA = "/usunUzytkownika";

    public static final String SCIEZKA_ADMINISTRACJA = ADMINISTRACJA + REST_PATH_SECURED;
    public static final String DZIENNIK_ZDARZEN = "/dziennikZdarzen";
}
