package pl.kancelaria.AHG.common.entityModel.users.user.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kancelaria.AHG.common.entityModel.users.user.UserOB;
import pl.kancelaria.AHG.common.entityModel.users.user.UserStateEnum;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserOB, Long> {

    UserOB findAllByUserName(String username);

    List<UserOB> findUserobsByActivationState(UserStateEnum activationState);

    List<UserOB> findByUserNameLike(String userName, Pageable pageable);

    @Query("SELECT u FROM UserOB u WHERE u.userName != 'deleted' AND LOWER(u.userName) LIKE %:userName% OR LOWER(u.surname) LIKE %:userName%")
    List<UserOB> searchByUserNameLike(@Param("userName") String userName, Pageable pageable);

    long countByUserNameLike(String userName);
}
