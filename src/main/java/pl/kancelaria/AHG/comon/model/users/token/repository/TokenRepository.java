package pl.kancelaria.AHG.comon.model.users.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kancelaria.AHG.comon.model.users.token.TokenOB;

/**
 * @author Michal
 * @created 10/07/2020
 */
@Repository
public interface TokenRepository extends JpaRepository<TokenOB, Long> {
    TokenOB findByToken(String token);
}
