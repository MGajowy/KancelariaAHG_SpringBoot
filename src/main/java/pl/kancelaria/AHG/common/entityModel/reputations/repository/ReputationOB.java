package pl.kancelaria.AHG.common.entityModel.reputations.repository;


import com.sun.istack.NotNull;
import lombok.Data;
import pl.kancelaria.AHG.common.entityModel.ModelConstants;

import javax.persistence.*;

@Entity
@Data
@Table(schema = ModelConstants.SCHEMA_REPUTATIONS, name = ModelConstants.TABLE_REPUTATION)
public class ReputationOB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ModelConstants.COLUMN_ID, length = 36)
    private long id;

    @NotNull
    private String userName;

    @NotNull
    @Column(name = ModelConstants.COLUMN_DESCRIPTION)
    private String description;

    @Column(name = ModelConstants.COLUMN_LIKE)
    private Long likeRep;

    @Column(name = ModelConstants.COLUMN_NOT_LIKE)
    private Long notLikeRep;

    private Boolean visible;

}
