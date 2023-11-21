package pl.kancelaria.AHG.common.entityModel.administration.orders.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kancelaria.AHG.common.entityModel.administration.orders.OrderOB;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderOB, Long> {

    @Query("SELECT o FROM OrderOB o WHERE LOWER(o.name) LIKE %:term% OR LOWER(o.surname) LIKE %:term%")
    List<OrderOB> findByNameAndSurname(@Param("term") String term, Pageable orderPageable);

    @Query("SELECT count(*) FROM OrderOB o WHERE LOWER(o.name) LIKE %:term% OR LOWER(o.surname) LIKE %:term%")
    long totalRecordSize(String term);
}
