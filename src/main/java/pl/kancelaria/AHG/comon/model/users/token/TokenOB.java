package pl.kancelaria.AHG.comon.model.users.token;

import lombok.Data;

import pl.kancelaria.AHG.comon.model.ModelConstants;
import pl.kancelaria.AHG.comon.model.users.user.UserOB;

import javax.persistence.*;

/**
 * @author Michal
 * @created 10/07/2020
 */
@Entity
@Table(schema = ModelConstants.SCHEMA_UZYTKOWNIK, name = ModelConstants.TABELA_Token)
@Data
public class TokenOB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ModelConstants.KOLUMNA_ID, length = 36)
    private long id;

    @Column(name = ModelConstants.KOLUMNA_token)
    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    private UserOB fk_uzytkownik;


    public TokenOB(UserOB user, String token) {
        this.fk_uzytkownik = user;
        this.token = token;}

}
