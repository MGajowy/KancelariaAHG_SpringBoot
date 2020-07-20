package pl.kancelaria.AHG.comon.model.users.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kancelaria.AHG.comon.model.users.token.TokenOB;

/**
 * @author Michal
 * @created 10/07/2020
 */
public interface TokenRepository extends JpaRepository<TokenOB, Long> {
}
