package pl.kancelaria.AHG.comon.model.administration.eventLog;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import pl.kancelaria.AHG.comon.model.ModelConstants;
import pl.kancelaria.AHG.comon.model.users.user.UserOB;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Michal
 * @created 06/01/2021
 */
@Entity
@Table(schema = ModelConstants.SCHEMA_ADMINISTRACJA, name = ModelConstants.TABELA_DZIENNIK_ZDARZEN)
@Data
public class EventLogOB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ModelConstants.KOLUMNA_ID, length = 36)
    private long id;

    @NotNull
    @Column(name = ModelConstants.KOLUMNA_CZYNNOSC, length = 255)
    private String czynnosc;

    @Column(name = ModelConstants.KOLUMNA_DATA_CZYNNOSCI)
    private Calendar data_czynnosci;

    @OneToOne(fetch = FetchType.LAZY)
    private UserOB uzytkownik;

//    @Column(name = ModelConstants.KOLUMNA_UZYTKOWNIK)
//    private Long id_uzytkownika;


    public EventLogOB() {
    }

    public EventLogOB(@NotNull String czynnosc, UserOB uzytkownik) {
        this.czynnosc = czynnosc;
        this.uzytkownik = uzytkownik;
    }
    public UserOB getUzytkownik() {
        return uzytkownik;
    }
}
