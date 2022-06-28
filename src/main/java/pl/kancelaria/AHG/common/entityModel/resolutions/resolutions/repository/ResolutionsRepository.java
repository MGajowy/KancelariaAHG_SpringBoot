package pl.kancelaria.AHG.common.entityModel.resolutions.resolutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kancelaria.AHG.common.entityModel.resolutions.resolutions.ResolutionsOB;


@Repository
public interface ResolutionsRepository extends JpaRepository<ResolutionsOB, Long> {
}
