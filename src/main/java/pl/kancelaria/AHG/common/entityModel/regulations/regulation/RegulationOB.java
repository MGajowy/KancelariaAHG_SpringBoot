package pl.kancelaria.AHG.common.entityModel.regulations.regulation;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kancelaria.AHG.common.entityModel.ModelConstants;
import pl.kancelaria.AHG.common.entityModel.regulations.category.CategoryRegulationOB;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = ModelConstants.TABLE_REGULATIONS, schema = ModelConstants.SCHEMA_REGULATIONS)
@Data
@NoArgsConstructor
public class RegulationOB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ModelConstants.COLUMN_ID,  length =36)
    private Long id;

    @Column (name = ModelConstants.COLUMN_ROLE_NAME, length = 300)
    private String regulationName;

    @Column(name = ModelConstants.COLUMN_CONTENTS, length = 5000)
    private String contents;

    @NotNull
    @Column(name = ModelConstants.COLUMN_IS_PUBLIC)
    private Boolean isPublic;

    @NotNull
    @Column (name = ModelConstants.COLUMN_DATE_ADDDED)
    private Date dateAdded;

    @ManyToOne
    @JoinColumn(name = "kategoria_fk")
    private CategoryRegulationOB category;
}
