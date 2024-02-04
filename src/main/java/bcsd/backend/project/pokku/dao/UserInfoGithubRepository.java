package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.domain.UserInfoGithub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoGithubRepository extends JpaRepository<UserInfoGithub, String> {

    @Query("SELECT p FROM UserInfoGithub p WHERE p.userInfo = :user_id")
    Optional<UserInfoGithub> findByUserId(@Param("user_id") UserInfo user);

    @Query("SELECT COUNT(p) FROM UserInfoGithub p WHERE p.userGithub = :sns")
    Long countById(@Param("sns") String snsName);

    @Query("DELETE FROM UserInfoGithub p WHERE p.userInfo = :user_id")
    @Modifying
    void deleteByUserId(@Param("user_id") UserInfo user);

    @Query("UPDATE UserInfoGithub p SET p.userGithub =:user_github WHERE p.userInfo = :user_id")
    @Modifying
    void updateByUserId(@Param("user_id") UserInfo user, @Param("user_github") String user_github);

}
