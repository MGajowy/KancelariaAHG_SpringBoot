package pl.kancelaria.AHG.shared.restapi;

/**
 * @author Michal
 * @created 09/08/2020
 */
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

}
