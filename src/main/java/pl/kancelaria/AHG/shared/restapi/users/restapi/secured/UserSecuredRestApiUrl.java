package pl.kancelaria.AHG.shared.restapi.users.restapi.secured;

import static pl.kancelaria.AHG.shared.restapi.RestApiUrlStale.*;

/**
 * @author Michal
 * @created 09/08/2020
 */
public class UserSecuredRestApiUrl {
    public static final String SCIEZKA_UZYTKOWNICY = UZYTKOWNICY + REST_PATH_SECURED;


    public static final String LISTA_UZYTWONIKOW = "/listaUzytkownikow";
    public static final String DODAJ_UZYTKOWNIKA = "/dodaj-uzytkownika";
    public static final String MODYFIKUJ_UZYTKOWNIKA = "/modyfikuj-uzytkownika";
    public static final String USUN_UZYTKOWNIKA = "/usun-uzytkownika";

}
