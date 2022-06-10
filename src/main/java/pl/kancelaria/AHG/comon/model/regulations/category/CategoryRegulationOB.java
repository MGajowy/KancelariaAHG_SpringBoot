package pl.kancelaria.AHG.comon.model.regulations.category;

import com.sun.istack.NotNull;
import lombok.Data;
import pl.kancelaria.AHG.comon.model.ModelConstants;
import pl.kancelaria.AHG.comon.model.regulations.regulation.RegulationOB;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = ModelConstants.TABELA_kategorie,schema = ModelConstants.SCHEMA_ROZPORZADZENIA)
@Data
public class CategoryRegulationOB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ModelConstants.KOLUMNA_ID, length = 36)
    private long id;

    @Column(name = ModelConstants.KOLUMNA_rodzaj_kategorii, length = 255)
    private  String rodzajKategorii;

    @NotNull
    @Column(name = ModelConstants.KOLUMNA_czy_publiczny)
    private Boolean czyPubliczny;

    @OneToMany(mappedBy="kategoria",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RegulationOB> rozporzadzenie = new ArrayList<>();
}
