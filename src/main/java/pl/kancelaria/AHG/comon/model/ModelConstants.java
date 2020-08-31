package pl.kancelaria.AHG.comon.model;

public class ModelConstants {
    //SCHEMATY
    public static final String SCHEMA_ADMINISTRACJA = "administracja";
    public  static final String SCHEMA_UCHWALY = "uchwaly";
    public  static final String SCHEMA_UZYTKOWNIK = "uzytkownik";
    public static final String SCHEMA_WSPOLNE = "wspolne";
    //TABELE
    public static final String TABELA_uzytkownik = "uzytkownik";
    public static final String TABELA_uchwaly = "uchwaly";
    public static final String TABELA_Token = "token";
    public static final String TABELA_kategorie = "kategorie";
    //KOLUMNY
    public static final String KOLUMNA_ID = "id";
    public static final String KOLUMNA_stan = "stan";
    //uzytkownik
    public static final String KOLUMNA_imie = "imie";
    public static final String KOLUMNA_nazwisko = "nazwisko";
    public static final String KOLUMNA_username = "username";
    public static final String KOLUMNA_password = "password";
    public static final String KOLUMNA_email = "email";
    public static final String KOLUMNA_czy_zaakceptowano_regulamin = "czy_zaakceptowano_regulamin";
    public static final String KOLUMNA_typ_konta = "typ_konta";
    public static final String KOLUMNA_telefon = "telefon";
    public static final String KOLUMNA_fk_token = "fk_token";
    public static final String KOLUMNA_zdjecie = "zdjecie_profilowe";
    public static final String KOLUMNA_plec = "plec";
    public static final String KOLUMNA_data_rejestracji = "data_rejestracji";
    //token
    public static final String KOLUMNA_token = "token";
    public static final String KOLUMNA_fk_uzytkownik = "fk_uzytkownik";
    //uchwały
    public static final String KOLUMNA_kategoria = "fk_kategoria";
    public static final String KOLUMNA_opis = "opis";
    public static final String KOLUMNA_tresc = "tresc";
    public static final String KOLUMNA_czy_publiczny = "czy_publiczny";
    //kategorie uchwał
    public static final String KOLUMNA_rodzaj_kategorii = "rodzaj_kategorii";
    public static final String KOLUMNA_fk_uchwały = "fk_uchwały";

}
