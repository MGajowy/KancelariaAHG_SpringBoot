package pl.kancelaria.AHG.common.entityModel.resolutions.resolutions.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.kancelaria.AHG.common.entityModel.resolutions.resolutions.ResolutionsOB;

import java.util.List;

public interface ResolutionRepositoryPage extends PagingAndSortingRepository<ResolutionsOB, Long> {
    List<ResolutionsOB> findByOpisLike(String opis, Pageable pageable);

    long countByOpisLike(String opis);
}
