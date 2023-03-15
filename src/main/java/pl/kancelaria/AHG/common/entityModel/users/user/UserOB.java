package pl.kancelaria.AHG.common.entityModel.users.user;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.kancelaria.AHG.common.entityModel.ModelConstants;
import pl.kancelaria.AHG.common.entityModel.users.roles.RolesOB;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(schema = ModelConstants.SCHEMA_USER, name = ModelConstants.TABLE_USER)
@Data
public class UserOB implements UserDetails {

    public UserOB() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ModelConstants.COLUMN_ID, length = 36)
    private long id;

    @Column(name = ModelConstants.COLUMN_NAME, length = 120)
    private String imie;

    @Column(name = ModelConstants.COLUMN_SURNAME, length = 250)
    private String nazwisko;

    @Column(name = ModelConstants.COLUMN_USERNAME, length = 255)
    private String userName;

    @Column(name = ModelConstants.COLUMN_PASSWORD, length = 255)
    private String password;


    @Column(name = ModelConstants.COLUMN_EMAIL)
    private String email;

    //    @Column (name = ModelConstants.KOLUMNA_czy_zaakceptowano_regulamin)
//    private Boolean czy_zaakceptowano_regulamin;
//
//    @Nullable
//    @Column(name = ModelConstants.KOLUMNA_typ_konta, length = 32, nullable = false)
//    @Enumerated(value = EnumType.STRING)
//    private UserAccountType typ_konta;
//
    @Column(name = ModelConstants.COLUMN_TELEPHONE, length = 50)
    private String telefon;

    @Column(name = ModelConstants.COLUMN_SEX, length = 9)
    @Enumerated(value = EnumType.STRING)
    private UserSexEnum plec;

//    @Column(name = ModelConstants.KOLUMNA_zdjecie, length = 36)
//    private Byte[] zdjecie_profilowe;


    @Column(name = ModelConstants.COLUMN_STATE, length = 32)
    @Enumerated(value = EnumType.STRING)
    private UserStateEnum stan;

    @NotNull
    @Column (name = ModelConstants.COLUMN_DATE_ADDDED)
    private Date dateAdded;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(schema = ModelConstants.SCHEMA_USER, name = "roles_users",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "rola_id"))
    private List<RolesOB> rolesOBSet;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<RolesOB> roles = getRolesOBSet();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (RolesOB role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.toString()));
        }
        return authorities;
//        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    @Nullable
//    @Column (name = ModelConstants.KOLUMNA_data_rejestracji, nullable = false)
//    private LocalDate dataRejestracji;

//    @OneToOne(fetch = FetchType.LAZY)
//    private TokenOB fk_token;

}
