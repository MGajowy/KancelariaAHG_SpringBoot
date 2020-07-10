package pl.kancelaria.AHG.comon.model.user.token;

import pl.kancelaria.AHG.comon.model.ModelConstants;
import pl.kancelaria.AHG.comon.model.user.user.UserOB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

//    todo  - dodać powiązanie z tabelą UserOB >>
    @Column(name = ModelConstants.KOLUMNA_fk_uzytkownik)
    private String fk_uzytkownik;

}
