package pl.kancelaria.AHG.common.entityModel.regulations.regulation.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kancelaria.AHG.common.entityModel.regulations.regulation.RegulationOB;

import java.util.List;

@Repository
public interface RegulationRepository extends JpaRepository<RegulationOB, Long> {

    List<RegulationOB> findByNazwaLike(String nazwa, Pageable pageable);

    long countByNazwaLike(String nazwa);
}
