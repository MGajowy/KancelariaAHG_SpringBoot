package pl.kancelaria.AHG.comon.model.user.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kancelaria.AHG.comon.model.user.user.UserOB;

@Repository
public interface UserRepository extends JpaRepository<UserOB, Long> {

}
