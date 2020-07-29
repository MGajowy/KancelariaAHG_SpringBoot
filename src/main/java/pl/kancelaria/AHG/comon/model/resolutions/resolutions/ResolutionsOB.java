package pl.kancelaria.AHG.comon.model.resolutions.resolutions;

import com.sun.istack.NotNull;
import lombok.Data;
import pl.kancelaria.AHG.comon.model.ModelConstants;
import pl.kancelaria.AHG.comon.model.resolutions.OrPublic;
import pl.kancelaria.AHG.comon.model.resolutions.categories.CategoriesOB;

import javax.persistence.*;

/**
 * @author Michal
 * @created 20/07/2020
 */
@Entity
@Table(schema = ModelConstants.SCHEMA_UCHWALY, name = ModelConstants.TABELA_uchwaly)
@Data
public class ResolutionsOB {
    @Id
    @Column(name = ModelConstants.KOLUMNA_ID, length = 36)
    private long id;

    @Column (name = ModelConstants.KOLUMNA_opis)
    private String opis;

    //todo zastanowić sie nad trescia!!
    @Column (name = ModelConstants.KOLUMNA_tresc)
    private String tresc;

    @NotNull
    @Column (name = ModelConstants.KOLUMNA_czy_publiczny, length = 9, nullable = false)
    @Enumerated(value = EnumType.STRING )
    private OrPublic czyPubliczny;

    @OneToOne(fetch = FetchType.LAZY)
    private CategoriesOB fk_kategorii;

}
