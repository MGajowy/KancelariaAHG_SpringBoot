package pl.kancelaria.AHG.comon.model.resolutions.resolutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kancelaria.AHG.comon.model.resolutions.resolutions.ResolutionsOB;

/**
 * @author Michal
 * @created 10/07/2020
 */
public interface ResolutionsRepository extends JpaRepository<ResolutionsOB, Long> {
}
