package pl.kancelaria.AHG.common.entityModel.users.token;

import lombok.Data;

import pl.kancelaria.AHG.common.entityModel.ModelConstants;
import pl.kancelaria.AHG.common.entityModel.users.user.UserOB;

import javax.persistence.*;


@Entity
@Table(schema = ModelConstants.SCHEMA_USER, name = ModelConstants.TABLE_TOKEN)
@Data
public class TokenOB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ModelConstants.COLUMN_ID, length = 36)
    private long id;

    @Column(name = ModelConstants.COLUMN_TOKEN)
    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    private UserOB fk_uzytkownik;

    public TokenOB() {
    }

    public TokenOB(long id, String token, UserOB fk_uzytkownik) {
        this.id = id;
        this.token = token;
        this.fk_uzytkownik = fk_uzytkownik;
    }

    public TokenOB(UserOB user, String token) {
        this.fk_uzytkownik = user;
        this.token = token;}

    public UserOB getFk_uzytkownik() {
        return fk_uzytkownik;
    }
}
