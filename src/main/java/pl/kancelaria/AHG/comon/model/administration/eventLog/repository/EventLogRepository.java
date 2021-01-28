package pl.kancelaria.AHG.comon.model.administration.eventLog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kancelaria.AHG.comon.model.administration.eventLog.EventLogOB;

/**
 * @author Michal
 * @created 06/01/2021
 */
@Repository
public interface EventLogRepository extends JpaRepository<EventLogOB, Long> {

}
