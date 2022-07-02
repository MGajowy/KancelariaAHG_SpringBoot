package pl.kancelaria.AHG.common.entityModel.resolutions.categories;

import com.sun.istack.NotNull;
import lombok.Data;
import pl.kancelaria.AHG.common.entityModel.ModelConstants;
import pl.kancelaria.AHG.common.entityModel.resolutions.resolutions.ResolutionsOB;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(schema = ModelConstants.SCHEMA_UCHWALY, name = ModelConstants.TABELA_kategorie)
@Data
public class CategoriesOB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ModelConstants.KOLUMNA_ID, length = 36)
    private long id;

    @Column(name = ModelConstants.KOLUMNA_rodzaj_kategorii, length = 255)
    private String rodzajKategorii;

    @NotNull
    @Column(name = ModelConstants.KOLUMNA_czy_publiczny)
    //@Enumerated(value = EnumType.STRING)
    private Boolean czyPubliczny;

    @OneToMany(mappedBy = "kategoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ResolutionsOB> uchwała = new ArrayList<>();

    public CategoriesOB(long id, String rodzajKategorii, boolean czyPubliczny) {
        this.id = id;
        this.rodzajKategorii = rodzajKategorii;
        this.czyPubliczny = czyPubliczny;
    }

    public CategoriesOB() {
    }

    public CategoryDTO listKategoriiPoStatus() {
        CategoryDTO dto = new CategoryDTO();
        dto.setRodzajKategorii(rodzajKategorii);
        dto.setCzyPubliczny(czyPubliczny);
        dto.setId(id);
        return dto;
    }
}
