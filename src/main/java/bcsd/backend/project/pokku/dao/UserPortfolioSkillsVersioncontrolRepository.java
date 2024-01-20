package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPortfolioSkillsVersioncontrolRepository extends JpaRepository<UserPortfolioSkillsVersioncontrol, Long> {

    @Query("SELECT count(p) FROM UserPortfolioSkillsVersioncontrol p WHERE p.userInfo = :user_id and p.skillsVersioncontrol = :skills_versioncontrol_id")
    Optional<Long> findEntityCnt(@Param("user_id") UserInfo user, @Param("skills_versioncontrol_id") SkillsVersioncontrol skillsVersioncontrol);

    @Query("SELECT p FROM UserPortfolioSkillsVersioncontrol p WHERE p.userInfo = :user_id and p.skillsVersioncontrol = :skills_versioncontrol_id")
    Optional<UserPortfolioSkillsVersioncontrol> findEntity(@Param("user_id") UserInfo user, @Param("skills_versioncontrol_id") SkillsVersioncontrol skillsVersioncontrol);

}
