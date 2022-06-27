package pl.kancelaria.AHG.comon.model.administration.eventLog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kancelaria.AHG.comon.model.administration.eventLog.EventLogOB;


@Repository
public interface EventLogRepository extends JpaRepository<EventLogOB, Long> {

}
