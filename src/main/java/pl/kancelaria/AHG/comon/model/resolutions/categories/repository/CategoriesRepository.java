package pl.kancelaria.AHG.comon.model.resolutions.categories.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kancelaria.AHG.comon.model.resolutions.categories.CategoriesOB;

/**
 * @author Michal
 * @created 20/07/2020
 */
public interface CategoriesRepository extends JpaRepository<CategoriesOB, Long> {
}
