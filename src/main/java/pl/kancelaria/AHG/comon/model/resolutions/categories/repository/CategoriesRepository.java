package pl.kancelaria.AHG.comon.model.resolutions.categories.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kancelaria.AHG.comon.model.resolutions.categories.CategoriesOB;

import java.util.List;


@Repository
public interface CategoriesRepository extends JpaRepository<CategoriesOB, Long> {

    @Query(value = "SELECT * FROM CategoriesOB c WHERE c.rodzajKategorii = :rodzaj", nativeQuery = true)
    List<CategoriesOB> findCategoriesByRodzajKategoriiImpl(@Param("rodzaj")String rodzajKategorii);
}
