package pl.kancelaria.AHG.common.entityModel.resolutions.resolutions;

import com.sun.istack.NotNull;
import lombok.Data;
import pl.kancelaria.AHG.common.entityModel.ModelConstants;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.CategoriesOB;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionDTO;

import javax.persistence.*;


@Entity
@Table(schema = ModelConstants.SCHEMA_UCHWALY, name = ModelConstants.TABELA_uchwaly)
@Data
public class ResolutionsOB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ModelConstants.KOLUMNA_ID, length = 36)
    private long id;

    @Column (name = ModelConstants.KOLUMNA_opis, length = 300)
    private String opis;

    @Column (name = ModelConstants.KOLUMNA_tresc, length = 5000)
    private String tresc;

    @NotNull
//    @Column (name = ModelConstants.KOLUMNA_czy_publiczny, length = 9, nullable = false)
//    @Enumerated(value = EnumType.STRING )
    private Boolean czyPubliczny;

    @ManyToOne
    @JoinColumn(name="fk_kategorii")
    private CategoriesOB kategoria;

    public ResolutionsOB() {
    }

    public ResolutionsOB(long id, String opis, String tresc, Boolean czyPubliczny, CategoriesOB kategoria) {
        this.id = id;
        this.opis = opis;
        this.tresc = tresc;
        this.czyPubliczny = czyPubliczny;
        this.kategoria = kategoria;
    }

    public CategoriesOB getKategoria() {
        return kategoria;
    }

    public void setKategoria(CategoriesOB kategoria) {
        this.kategoria = kategoria;
    }

    public ResolutionDTO getResolutionDTO() {
        ResolutionDTO resolutionDTO = new ResolutionDTO();
        resolutionDTO.setId(id);
        resolutionDTO.setCzyPubliczny(czyPubliczny);
        resolutionDTO.setOpis(opis);
        resolutionDTO.setTresc(tresc);
        resolutionDTO.setNazwaKategorii(kategoria.getRodzajKategorii());
        return resolutionDTO;
    }
}
