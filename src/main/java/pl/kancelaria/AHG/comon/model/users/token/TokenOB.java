package pl.kancelaria.AHG.comon.model.users.token;

import pl.kancelaria.AHG.comon.model.ModelConstants;
import pl.kancelaria.AHG.comon.model.users.user.UserOB;

import javax.persistence.*;

/**
 * @author Michal
 * @created 10/07/2020
 */
@Entity
@Table(schema = ModelConstants.SCHEMA_UZYTKOWNIK, name = ModelConstants.TABELA_Token)
public class TokenOB {
    @Id
    @Column(name = ModelConstants.KOLUMNA_ID, length = 36)
    private long id;

    @Column(name = ModelConstants.KOLUMNA_token)
    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    private UserOB fk_uzytkownik;

}
