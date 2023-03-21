package pl.kancelaria.AHG.common.entityModel.regulations.category;

import com.sun.istack.NotNull;
import lombok.Data;
import pl.kancelaria.AHG.common.entityModel.ModelConstants;
import pl.kancelaria.AHG.common.entityModel.regulations.regulation.RegulationOB;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = ModelConstants.TABLE_CATEGORY,schema = ModelConstants.SCHEMA_REGULATIONS)
@Data
public class CategoryRegulationOB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ModelConstants.COLUMN_ID, length = 36)
    private long id;

    @Column(name = ModelConstants.COLUMN_TYPE_OF_CATEGORY, length = 255)
    private  String rodzajKategorii;

    @NotNull
    @Column(name = ModelConstants.COLUMN_IS_PUBLIC)
    private Boolean czyPubliczny;

    @OneToMany(mappedBy= "category",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RegulationOB> rozporzadzenie = new ArrayList<>();
}
