package pl.kancelaria.AHG.common.entityModel.document;

import lombok.*;
import pl.kancelaria.AHG.common.entityModel.ModelConstants;
import pl.kancelaria.AHG.common.entityModel.users.user.UserOB;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = ModelConstants.TABLE_DOCKUMENT, schema = ModelConstants.SCHEMA_DOCKUMENTS)
@Data
@NoArgsConstructor
public class DocumentOB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ModelConstants.COLUMN_ID, length = 36)
    private long id;
    private String docName;
    private String docType;
//    @Lob
    private byte[] data;
    private LocalDate createDate;
    private LocalDate deleteDate;
    private String status;
    @ManyToOne
    @JoinColumn(name = "userid")
    private UserOB userid;

    public DocumentOB(long id, String docName, String docType, byte[] data, LocalDate createDate, LocalDate deleteDate, String status, UserOB userid) {
        this.id = id;
        this.docName = docName;
        this.docType = docType;
        this.data = data;
        this.createDate = createDate;
        this.deleteDate = deleteDate;
        this.status = status;
        this.userid = userid;
    }
}