package pl.kancelaria.AHG.shared.restapi.administration.restapi.secured;

import static pl.kancelaria.AHG.shared.restapi.RestApiUrlStale.*;


public class AdministrationSecuredRestApiUrl {
    //todo sprawdzic sciezke czy jest obsluzona.
    public static final String USUN_UZYTKOWNIKA = "/usunUzytkownika";

    public static final String SCIEZKA_ADMINISTRACJA = ADMINISTRACJA + REST_PATH_SECURED;
    public static final String DZIENNIK_ZDARZEN = "/dziennikZdarzen";
    public static final String EXPORT_TO_PDF = "/exportPDF";
}
