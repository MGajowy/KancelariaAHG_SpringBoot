package pl.kancelaria.AHG.common.entityModel.administration.orders;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kancelaria.AHG.common.entityModel.ModelConstants;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table (name = ModelConstants.TABLE_ORDERS, schema = ModelConstants.SCHEMA_ADMINISTRATION)
@NoArgsConstructor
@Getter
@Setter
public class OrderOB {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = ModelConstants.COLUMN_ID, length = 36)
    private long id;
    private String name;
    private String surname;
    private Double sum;
    private Boolean orInstallments;
    private Long numberOfInstallments;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfAdmission;
    private LocalDate endDate;
    private String caseType;
}
