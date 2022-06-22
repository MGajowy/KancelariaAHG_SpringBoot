package pl.kancelaria.AHG.comon.model.resolutions.categories;

import com.sun.istack.NotNull;
import lombok.Data;
import pl.kancelaria.AHG.comon.model.ModelConstants;
import pl.kancelaria.AHG.comon.model.resolutions.OrPublic;
import pl.kancelaria.AHG.comon.model.resolutions.resolutions.ResolutionsOB;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name = ModelConstants.KOLUMNA_czy_publiczny)
    //@Enumerated(value = EnumType.STRING)
    private Boolean czyPubliczny;

    @OneToMany(mappedBy="kategoria",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ResolutionsOB> uchwała = new ArrayList<>();

    public CategoriesOB(long id, String rodzjaKategorii, boolean czyPubliczny) {
        this.id = id;
        this.rodzajKategorii = rodzjaKategorii;
        this.czyPubliczny = czyPubliczny;
    }

    public CategoriesOB() {

    }
}
