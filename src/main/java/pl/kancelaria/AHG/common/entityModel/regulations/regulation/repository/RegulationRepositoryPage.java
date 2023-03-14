package pl.kancelaria.AHG.common.entityModel.regulations.regulation.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.kancelaria.AHG.common.entityModel.regulations.regulation.RegulationOB;

import java.util.List;

public interface RegulationRepositoryPage extends PagingAndSortingRepository<RegulationOB, Long> {

    List<RegulationOB> findByNazwaLike(String nazwa, Pageable pageable);

    long countByNazwaLike(String nazwa);
}
