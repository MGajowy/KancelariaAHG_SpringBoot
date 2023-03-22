package pl.kancelaria.AHG.common.entityModel.users.roles;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.kancelaria.AHG.common.entityModel.ModelConstants;
import pl.kancelaria.AHG.common.entityModel.users.user.UserOB;
import pl.kancelaria.AHG.user.role.RolesName;
import javax.persistence.*;
import java.util.List;



@Entity
@Table(schema = ModelConstants.SCHEMA_USER, name = ModelConstants.TABLE_ROLE)
@Data
@EqualsAndHashCode(exclude = "userOBSet")
public class RolesOB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = ModelConstants.COLUMN_ROLE_ID, length = 36)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column (name = ModelConstants.COLUMN_ROLE_NAME, length = 255)
    private RolesName rolesName;

    // ta encja musi być zapisana w 1 kolejności !! wazne!
    @ManyToMany(mappedBy = "rolesOBSet")
    private List<UserOB> userOBSet;

    @Override
    public String toString() {
        return "RolesOB{" +
                "nazwa=" + rolesName +
                '}';

}
}
