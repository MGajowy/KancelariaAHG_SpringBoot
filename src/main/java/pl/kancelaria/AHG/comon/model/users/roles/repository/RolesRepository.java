package pl.kancelaria.AHG.comon.model.users.roles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kancelaria.AHG.comon.model.users.roles.RolesOB;
import pl.kancelaria.AHG.user.role.RolesName;


@Repository
public interface RolesRepository extends JpaRepository<RolesOB, Long> {
    RolesOB findAllByNazwa(RolesName nazwa);

}
