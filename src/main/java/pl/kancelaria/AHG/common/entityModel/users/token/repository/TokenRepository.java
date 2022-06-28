package pl.kancelaria.AHG.common.entityModel.users.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kancelaria.AHG.common.entityModel.users.token.TokenOB;


@Repository
public interface TokenRepository extends JpaRepository<TokenOB, Long> {
    TokenOB findByToken(String token);
}
