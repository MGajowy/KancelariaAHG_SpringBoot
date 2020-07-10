package pl.kancelaria.AHG.comon.model.user.user;

import com.sun.istack.internal.NotNull;
import pl.kancelaria.AHG.comon.model.ModelConstants;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Michal
 * @created 10/07/2020
 */
@Entity
@Table(schema = ModelConstants.SCHEMA_UZYTKOWNIK, name = ModelConstants.TABELA_uzytkownik)
public class UserOB {

    @Id
    @Column (name = ModelConstants.KOLUMNA_ID, length = 36)
    private long id;

    @Column(name = ModelConstants.KOLUMNA_imie, length = 120)
    private String imie;

    @Column(name = ModelConstants.KOLUMNA_nazwisko, length = 250)
    private String nazwisko;

    @Column(name = ModelConstants.KOLUMNA_login, length = 255)
    private String login;

    @Column(name = ModelConstants.KOLUMNA_email)
    private String email;

    @Column (name = ModelConstants.KOLUMNA_czy_zaakceptowano_regulamin)
    private Boolean czy_zaakceptowano_regulamin;

    @NotNull
    @Column(name = ModelConstants.KOLUMNA_typ_konta, length = 32, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserAccountType typ_konta;

    @Column(name = ModelConstants.KOLUMNA_telefon,length = 50)
    private String telefon;

//    todo -  zrobić tokenOB i zrobić powiązanie z tabela token
    @Column(name = ModelConstants.KOLUMNA_fk_token)
    private String fk_token;

    @Column (name = ModelConstants.KOLUMNA_plec, length = 9)
    @Enumerated(value = EnumType.STRING)
    private UserSexEnum plec;

    @Column(name = ModelConstants.KOLUMNA_zdjecie, length = 36)
    private String zdjecie_profilowe;

    @NotNull
    @Column(name = ModelConstants.KOLUMNA_stan, length = 32, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserStateEnum stan;

    @NotNull
    @Column (name = ModelConstants.KOLUMNA_data_rejestracji, nullable = false)
    private Date dataRejestracji;


//    static public UserOB utworz(){
//        UserOB userOB = new UserOB();
//        userOB.setStan(UserStateEnum.NIEAKTYWNY);
//        userOB.setDataRejestracji(new Date());
//        return userOB;
//    }


}
