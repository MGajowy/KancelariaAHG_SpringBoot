package pl.kancelaria.AHG.common.entityModel.resolutions.resolutions;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kancelaria.AHG.common.entityModel.ModelConstants;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.CategoriesOB;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionDTO;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(schema = ModelConstants.SCHEMA_RESOLUTIONS, name = ModelConstants.TABLE_RESOLUTIONS)
@Data
@NoArgsConstructor
public class ResolutionsOB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ModelConstants.COLUMN_ID, length = 36)
    private long id;

    @Column (name = ModelConstants.COLUMN_DESCRIPTION_OPIS, length = 300)
    private String resolutionName;

    @Column (name = ModelConstants.COLUMN_CONTENTS, length = 5000)
    private String contents;

    @NotNull
//    @Column (name = ModelConstants.KOLUMNA_czy_publiczny, length = 9, nullable = false)
//    @Enumerated(value = EnumType.STRING )
    private Boolean isPublic;

    @NotNull
    @Column (name = ModelConstants.COLUMN_DATE_ADDDED)
    private Date dateAdded;

    @ManyToOne
    @JoinColumn(name="fk_kategorii")
    private CategoriesOB category;

    public ResolutionsOB(long id, String resolutionName, String contents, Boolean isPublic, CategoriesOB category) {
        this.id = id;
        this.resolutionName = resolutionName;
        this.contents = contents;
        this.isPublic = isPublic;
        this.category = category;
    }

    public CategoriesOB getCategory() {
        return category;
    }

    public void setCategory(CategoriesOB category) {
        this.category = category;
    }

    public ResolutionDTO getResolutionDTO() {
        ResolutionDTO resolutionDTO = new ResolutionDTO();
        resolutionDTO.setId(id);
        resolutionDTO.setIsPublic(isPublic);
        resolutionDTO.setResolutionName(resolutionName);
        resolutionDTO.setContents(contents);
        resolutionDTO.setCategoryName(category.getCategoryName());
        return resolutionDTO;
    }
}
