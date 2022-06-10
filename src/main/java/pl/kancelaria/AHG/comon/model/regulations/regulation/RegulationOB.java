package pl.kancelaria.AHG.comon.model.regulations.regulation;

import lombok.Data;
import lombok.NonNull;
import pl.kancelaria.AHG.comon.model.ModelConstants;
import pl.kancelaria.AHG.comon.model.regulations.category.CategoryRegulationOB;

import javax.persistence.*;

@Entity
@Table(name = ModelConstants.TABELA_ROZPORZADZENIA, schema = ModelConstants.SCHEMA_ROZPORZADZENIA )
@Data
public class RegulationOB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ModelConstants.KOLUMNA_ID,  length =36)
    private Long id;

    @Column (name = ModelConstants.KOLUMNA_ROLA_NAZWA)
    private String nazwa;

    @Column(name = ModelConstants.KOLUMNA_tresc)
    private String tresc;

    @NonNull
    @Column(name = ModelConstants.KOLUMNA_czy_publiczny)
    private Boolean czy_publiczny;

    @ManyToOne
    @JoinColumn(name = "kategoria_fk")
    private CategoryRegulationOB kategoria;
}
