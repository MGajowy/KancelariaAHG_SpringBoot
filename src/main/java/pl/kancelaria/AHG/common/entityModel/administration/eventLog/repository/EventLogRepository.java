package pl.kancelaria.AHG.common.entityModel.administration.eventLog.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kancelaria.AHG.common.entityModel.administration.eventLog.EventLogOB;

import java.util.List;


@Repository
public interface EventLogRepository extends JpaRepository<EventLogOB, Long> {

    List<EventLogOB> findByActionLike(String action, Pageable pageable);

    long countByActionLike(String action);
}
