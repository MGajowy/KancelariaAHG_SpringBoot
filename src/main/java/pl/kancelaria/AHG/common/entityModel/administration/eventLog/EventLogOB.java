package pl.kancelaria.AHG.common.entityModel.administration.eventLog;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import pl.kancelaria.AHG.common.entityModel.ModelConstants;

import javax.persistence.*;
import java.util.Calendar;


@Entity
@Table(schema = ModelConstants.SCHEMA_ADMINISTRATION, name = ModelConstants.TABLE_EVENT_LOG)
@Data
public class EventLogOB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ModelConstants.COLUMN_ID, length = 36)
    private long id;

    @NotNull
    @Column(name = ModelConstants.COLUMN_ACTION, length = 255)
    private String czynnosc;

    @Column(name = ModelConstants.COLUMN_DATE_ACTION)
    private Calendar data_czynnosci;

//    @OneToOne(fetch = FetchType.LAZY)
    private String uzytkownik;

    public EventLogOB() {
    }

    public EventLogOB(@NotNull String czynnosc, String uzytkownik) {
        this.czynnosc = czynnosc;
        this.uzytkownik = uzytkownik;
    }
    public String getUzytkownik() {
        return uzytkownik;
    }
}
