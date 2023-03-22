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
    private UserOB user;

    public TokenOB() {
    }

    public TokenOB(long id, String token, UserOB user) {
        this.id = id;
        this.token = token;
        this.user = user;
    }

    public TokenOB(UserOB user, String token) {
        this.user = user;
        this.token = token;}

    public UserOB getUser() {
        return user;
    }
}
