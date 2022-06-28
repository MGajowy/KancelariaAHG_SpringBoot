package pl.kancelaria.AHG.common.entityModel.regulations.regulation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kancelaria.AHG.common.entityModel.regulations.regulation.RegulationOB;

@Repository
public interface RegulationRepository extends JpaRepository<RegulationOB, Long> {
}
