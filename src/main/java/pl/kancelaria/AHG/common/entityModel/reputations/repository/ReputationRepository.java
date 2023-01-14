package pl.kancelaria.AHG.common.entityModel.reputations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReputationRepository extends JpaRepository<ReputationOB, Long> {
}
