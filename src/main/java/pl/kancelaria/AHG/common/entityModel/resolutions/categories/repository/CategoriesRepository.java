package pl.kancelaria.AHG.common.entityModel.resolutions.categories.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.CategoriesOB;

import java.util.List;
import java.util.Optional;


@Repository
public interface CategoriesRepository extends JpaRepository<CategoriesOB, Long> {

    Optional<CategoriesOB> findById(Long id);

    @Query(value = "SELECT * FROM CategoriesOB c WHERE c.rodzajKategorii = :rodzajKategorii", nativeQuery = true)
    List<CategoriesOB> findCategoriesByRodzajKategoriiImpl(@Param("rodzajKategorii")String rodzajKategorii);

    List<CategoriesOB> findCategoriesOBsByRodzajKategorii(String rodzajKategorii);
}
