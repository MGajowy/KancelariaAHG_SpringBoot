package pl.kancelaria.AHG.shared.restapi;


public class RestApiUrlStale {
    public static final String REST_PATH = "/rest";
    public static final String LOGIN = "/authenticate";
    public static final String REJESTRACJA = "/register";
    public static final String USTAW_HASLO = "/ustaw-haslo";
    public static final String RESET_HASLA = "/reset-hasla";

    //     dostępy   rest/ ...../
    public static final String REST_PATH_SECURED = "/secured";
    public static final String REST_PATH_PUBLIC = "/pub";

    //   sciezki modululow  rest/......
    public static final String UZYTKOWNICY = REST_PATH + "/uzytkownicy";
    public static final String KATEGORIE = REST_PATH + "/kategorie";
    public static final String UCHWALY = REST_PATH + "/uchwaly";
    public static final String ROZPORZADZENIA = REST_PATH + "/rozporzadzenia";
    public static final String KATEGORIE_ROZPORZADZEN = REST_PATH + "/kategorieRozporzadzen";
    public static final String ADMINISTRACJA = REST_PATH + "/administracja";
    public static final String POMOC_ONLINE = REST_PATH + "/pomocOnline";
    public static final String REPUTATION = REST_PATH + "/reputation";
    public static final String DOCUMENT = REST_PATH + "/document";
    public static final String SAOS = REST_PATH + "/saos";
    public static final String CHECK_LOGIN = "/checkLogin";
}
