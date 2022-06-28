package pl.kancelaria.AHG.common.entityModel.regulations.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kancelaria.AHG.common.entityModel.regulations.category.CategoryRegulationOB;

@Repository
public interface CategoryRegulationRepository extends JpaRepository<CategoryRegulationOB, Long> {
}
