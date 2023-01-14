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
@Table(schema = ModelConstants.SCHEMA_RESOLUTIONS, name = ModelConstants.TABLE_CATEGORY)
@Data
public class CategoriesOB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ModelConstants.COLUMN_ID, length = 36)
    private long id;

    @Column(name = ModelConstants.COLUMN_TYPE_OF_CATEGORY, length = 255)
    private String rodzajKategorii;

    @NotNull
    @Column(name = ModelConstants.COLUMN_IS_PUBLIC)
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

    public CategoryDTO categoryListByStatus() {
        CategoryDTO dto = new CategoryDTO();
        dto.setRodzajKategorii(rodzajKategorii);
        dto.setCzyPubliczny(czyPubliczny);
        dto.setId(id);
        return dto;
    }
}
