package pl.kancelaria.AHG.shared.restapi.users.restapi.secured;

import static pl.kancelaria.AHG.shared.restapi.RestApiUrlStale.*;


public class UserSecuredRestApiUrl {
    public static final String SCIEZKA_UZYTKOWNICY = UZYTKOWNICY + REST_PATH_SECURED;

    public static final String LISTA_UZYTWONIKOW = "/listaUzytkownikow";
    public static final String UZYTKOWNICY_DOKUMENTY = "/uzytkownicy";
    public static final String DODAJ_UZYTKOWNIKA = "/dodaj-uzytkownika";
    public static final String MODYFIKUJ_UZYTKOWNIKA = "/modyfikuj-uzytkownika";
    public static final String USUN_UZYTKOWNIKA = "/usun-uzytkownika";
    public static final String WYSLIJ_EMAIL_AKTYWACYJNY = "/wyslij-email-aktywacyjny";
    public static final String WERYFIKUJ_TOKEN = "/weryfikuj-token";
    public static final String DEZAKTUWUJ_UZYTKOWNIKA = "/dezaktywuj-uzytkownika";
    public static final String SZCZEGOLY_UZYTKOWNIKA = "/szczegoly-uzytkownika";


    public static final String UZYTKOWNICY_PO_STAN = "/listaUzytkownikowStan";
}
