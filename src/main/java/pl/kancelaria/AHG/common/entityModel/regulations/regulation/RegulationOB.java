package pl.kancelaria.AHG.common.entityModel.regulations.regulation;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import pl.kancelaria.AHG.common.entityModel.ModelConstants;
import pl.kancelaria.AHG.common.entityModel.regulations.category.CategoryRegulationOB;

import javax.persistence.*;

@Entity
@Table(name = ModelConstants.TABELA_ROZPORZADZENIA, schema = ModelConstants.SCHEMA_ROZPORZADZENIA )
@Data
@NoArgsConstructor
public class RegulationOB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ModelConstants.KOLUMNA_ID,  length =36)
    private Long id;

    @Column (name = ModelConstants.KOLUMNA_ROLA_NAZWA, length = 300)
    private String nazwa;

    @Column(name = ModelConstants.KOLUMNA_tresc, length = 5000)
    private String tresc;

    @NonNull
    @Column(name = ModelConstants.KOLUMNA_czy_publiczny)
    private Boolean czyPubliczny;

    @ManyToOne
    @JoinColumn(name = "kategoria_fk")
    private CategoryRegulationOB kategoria;
}
