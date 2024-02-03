package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.SkillsMobileapp;
import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.domain.UserPortfolioSkillsMobileapp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPortfolioSkillsMobileappRepository extends JpaRepository<UserPortfolioSkillsMobileapp, Long> {

    @Query("SELECT count(p) FROM UserPortfolioSkillsMobileapp p WHERE p.userInfo = :user_id and p.skillsMobileapp = :skills_mobileapp_id")
    Optional<Long> findEntityCnt(@Param("user_id") UserInfo user, @Param("skills_mobileapp_id") SkillsMobileapp skillsMobileapp);

    @Query("SELECT p FROM UserPortfolioSkillsMobileapp p WHERE p.userInfo = :user_id and p.skillsMobileapp = :skills_mobileapp_id")
    Optional<UserPortfolioSkillsMobileapp> findEntity(@Param("user_id") UserInfo user, @Param("skills_mobileapp_id") SkillsMobileapp skillsMobileapp);

    @Query("SELECT p.skillsMobileapp FROM UserPortfolioSkillsMobileapp p WHERE p.userInfo = :user_id")
    List<Long> findByUserId(@Param("user_id") UserInfo user);

}
