package pl.kancelaria.AHG.shared.restapi.modules.categoriesRegulations.restApi.pub;

import static pl.kancelaria.AHG.shared.restapi.RestApiUrlStale.KATEGORIE_ROZPORZADZEN;
import static pl.kancelaria.AHG.shared.restapi.RestApiUrlStale.REST_PATH_PUBLIC;

public class CategoryRegulationPublicRestApiUrl {
    public static final String SCIEZKA_KATEGORIE_ROZPORZADZEN = KATEGORIE_ROZPORZADZEN + REST_PATH_PUBLIC;
    public static final String WSZYSTKIE_KATEGORIE_ROZPORZADZEN = "/wszystkieKategorieRozporzadzen";
    public static final String SZCZEGOLY_ROZPORZADZENIA = "/szczegoly";
    public static final String WYSZUKAJ_KATEGORIE_ROZPORZADZEN = "/wyszukajKategorieRozporzadzen";
    public static final String WYSZUKAJ_KATEGORIE_ROZPORZADZENIA_PO_NAZWA = "/wyszukajKategoieRozporzadzeniaPoNazwa";
}
