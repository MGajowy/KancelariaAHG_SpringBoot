package pl.kancelaria.AHG.comon.model.users.user;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import pl.kancelaria.AHG.comon.model.ModelConstants;
import pl.kancelaria.AHG.comon.model.users.token.TokenOB;

import javax.persistence.*;

/**
 * @author Michal
 * @created 10/07/2020
 */

@Entity
@Table(schema = ModelConstants.SCHEMA_UZYTKOWNIK, name = ModelConstants.TABELA_uzytkownik)
@Data
public class UserOB {

    public UserOB() {
    }

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

    @Column (name = ModelConstants.KOLUMNA_plec, length = 9)
    @Enumerated(value = EnumType.STRING)
    private UserSexEnum plec;

//    @Column(name = ModelConstants.KOLUMNA_zdjecie, length = 36)
//    private Byte[] zdjecie_profilowe;

    @NotNull
    @Column(name = ModelConstants.KOLUMNA_stan, length = 32, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserStateEnum stan;

//    @NotNull
//    @Column (name = ModelConstants.KOLUMNA_data_rejestracji, nullable = false)
//    private LocalDate dataRejestracji;

    @OneToOne(fetch = FetchType.LAZY)
    private TokenOB fk_token;

//        static public UserOB utworz(){
//        UserOB userOB = new UserOB();
//        userOB.setStan(UserStateEnum.NIEAKTYWNY);
//        userOB.setDataRejestracji(new LocalDate());
//        return userOB;
//    }


}
