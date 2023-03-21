package pl.kancelaria.AHG.common.entityModel.administration.eventLog;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import pl.kancelaria.AHG.common.entityModel.ModelConstants;

import javax.persistence.*;
import java.util.Date;


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
    private String action;

    @Column(name = ModelConstants.COLUMN_DATE_ACTION)
    private Date dateAction;

//    @OneToOne(fetch = FetchType.LAZY)
    private String userName;

    public EventLogOB() {
    }

    public EventLogOB(@NotNull String action, String userName) {
        this.action = action;
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }
}
