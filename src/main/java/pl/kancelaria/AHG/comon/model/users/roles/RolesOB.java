package pl.kancelaria.AHG.comon.model.users.roles;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.kancelaria.AHG.comon.model.ModelConstants;
import pl.kancelaria.AHG.comon.model.users.user.UserOB;
import pl.kancelaria.AHG.user.role.RolesName;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Michal
 * @created 02/11/2020
 */
@Entity
@Table(schema = ModelConstants.SCHEMA_UZYTKOWNIK, name = ModelConstants.TABELA_ROLE)
@Data
@EqualsAndHashCode(exclude = "userOBSet")
public class RolesOB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = ModelConstants.KOLUMNA_ROLE_ID, length = 36)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column (name = ModelConstants.KOLUMNA_ROLA_NAZWA, length = 255)
    private RolesName nazwa;

// ta encja musi być zapisana w 1 kolejności !! wazne!
    @ManyToMany(mappedBy = "rolesOBSet")
    private List<UserOB> userOBSet;

    @Override
    public String toString() {
        return "RolesOB{" +
                "nazwa=" + nazwa +
                '}';

}
}
