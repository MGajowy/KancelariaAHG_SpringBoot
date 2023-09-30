package pl.kancelaria.AHG.common.entityModel.document.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.kancelaria.AHG.common.entityModel.document.DocumentOB;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentOB, Long> {

    @Query(value = "SELECT d FROM DocumentOB d WHERE (d.userid.id = ?1 AND d.status = ?3) AND LOWER(d.docName) like %?2%")
    List<DocumentOB> searchByUserIdAndTerm(Long userid, String term, String status, Pageable pageable);

    @Override
    Page<DocumentOB> findAll(Pageable pageable);
    List<DocumentOB> findByStatusLike(String status);
    long countByStatusLike(String docName);
    @Query(value = "SELECT count(*) FROM DocumentOB d WHERE d.userid.id = ?1 AND d.status = 'PUBLIC' ")
    long totalRecordsSize(Long id);
}