package pl.kancelaria.AHG.common.entityModel.resolutions.categories;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kancelaria.AHG.common.entityModel.ModelConstants;
import pl.kancelaria.AHG.common.entityModel.resolutions.resolutions.ResolutionsOB;
import pl.kancelaria.AHG.modules.categories.dto.CategoryDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(schema = ModelConstants.SCHEMA_RESOLUTIONS, name = ModelConstants.TABLE_CATEGORY)
@Data
@NoArgsConstructor
public class CategoriesOB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ModelConstants.COLUMN_ID, length = 36)
    private long id;

    @Column(name = ModelConstants.COLUMN_TYPE_OF_CATEGORY, length = 255)
    private String categoryName;

    @NotNull
    @Column(name = ModelConstants.COLUMN_IS_PUBLIC)
    //@Enumerated(value = EnumType.STRING)
    private Boolean isPublic;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ResolutionsOB> resolutions = new ArrayList<>();

    public CategoriesOB(long id, String categoryName, boolean isPublic) {
        this.id = id;
        this.categoryName = categoryName;
        this.isPublic = isPublic;
    }

    public CategoryDTO categoryListByStatus() {
        CategoryDTO dto = new CategoryDTO();
        dto.setCategoryName(categoryName);
        dto.setIsPublic(isPublic);
        dto.setId(id);
        return dto;
    }
}
