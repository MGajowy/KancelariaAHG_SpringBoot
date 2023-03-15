package pl.kancelaria.AHG.common.entityModel.resolutions.resolutions.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kancelaria.AHG.common.entityModel.resolutions.resolutions.ResolutionsOB;

import java.util.List;

@Repository
public interface ResolutionsRepository extends JpaRepository<ResolutionsOB, Long> {
    List<ResolutionsOB> findByOpisLike(String opis, Pageable pageable);

    long countByOpisLike(String opis);
}
