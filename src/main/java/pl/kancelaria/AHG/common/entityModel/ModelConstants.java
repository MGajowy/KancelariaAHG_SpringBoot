package pl.kancelaria.AHG.common.entityModel;

public class ModelConstants {
    //SCHEMATY
    public static final String SCHEMA_ADMINISTRATION = "administracja";
    public static final String SCHEMA_RESOLUTIONS = "uchwaly";
    public static final String SCHEMA_USER = "uzytkownik";
    public static final String SCHEMA_SHARED = "wspolne";
    public static final String SCHEMA_REGULATIONS = "rozporzadzenia";

    //TABELE
    public static final String TABLE_USER = "uzytkownik";
    public static final String TABLE_RESOLUTIONS = "uchwaly";
    public static final String TABLE_TOKEN = "token";
    public static final String TABLE_CATEGORY = "kategorie";
    public static final String TABLE_ROLE = "role";
    public static final String TABLE_EVENT_LOG = "dziennik_zdarzen";
    public static final String TABLE_REGULATIONS = "rozporzadzenia";

    //KOLUMNY
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_STATE = "stan";

    //uzytkownik
    public static final String COLUMN_NAME = "imie";
    public static final String COLUMN_SURNAME = "nazwisko";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_ACCEPT_STATUE = "czy_zaakceptowano_regulamin";
    public static final String COLUMN_TYPE_ACCOUNT = "typ_konta";
    public static final String COLUMN_TELEPHONE = "telefon";
    public static final String COLUMN_FK_TOKEN = "fk_token";
    public static final String COLUMN_FOTO_PROFILE = "zdjecie_profilowe";
    public static final String COLUMN_SEX = "plec";
    public static final String COLUMN_DATE_OF_REGISTRATION = "data_rejestracji";

    //token
    public static final String COLUMN_TOKEN = "token";
    public static final String COLUMN_FK_USER = "fk_uzytkownik";

    //uchwały, rozporzadzenia
    public static final String COLUMN_FK_CATEGORY = "fk_kategoria";
    public static final String COLUMN_DESCRIPTION_OPIS = "opis";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_CONTENTS = "tresc";
    public static final String COLUMN_IS_PUBLIC = "czy_publiczny";
    public static final String COLUMN_DATE_ADDDED = "data_dodania";


    //kategorie uchwał
    public static final String COLUMN_TYPE_OF_CATEGORY = "rodzaj_kategorii";
    public static final String COLUMN_FK_RESOLUTION = "fk_uchwały";

    //role
    public static final String COLUMN_ROLE_ID = "rola_id";
    public static final String COLUMN_ROLE_NAME = "nazwa";

    //dziennik zdarzen
    public static final String COLUMN_ACTION = "czynnosc";
    public static final String COLUMN_DATE_ACTION = "data_czynnosci";
    public static final String COLUMN_USER_ACTION = "uzytkownik";

    //reputation
    public static final String COLUMN_LIKE = "like_reputation";
    public static final String COLUMN_NOT_LIKE = "notLike_reputation";

}
