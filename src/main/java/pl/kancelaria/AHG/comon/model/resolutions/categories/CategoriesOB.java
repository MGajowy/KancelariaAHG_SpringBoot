package pl.kancelaria.AHG.comon.model.resolutions.categories;

import com.sun.istack.NotNull;
import lombok.Data;
import pl.kancelaria.AHG.comon.model.ModelConstants;
import pl.kancelaria.AHG.comon.model.resolutions.OrPublic;

import javax.persistence.*;

/**
 * @author Michal
 * @created 20/07/2020
 */
@Entity
@Table(schema = ModelConstants.SCHEMA_UCHWALY, name = ModelConstants.TABELA_kategorie)
@Data
public class CategoriesOB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ModelConstants.KOLUMNA_ID, length = 36)
    private long id;

    @Column(name = ModelConstants.KOLUMNA_rodzaj_kategorii, length = 255)
    private  String rodzajKategorii;

    @NotNull
    @Column(name = ModelConstants.KOLUMNA_czy_publiczny, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private OrPublic czyPubliczny;
}
