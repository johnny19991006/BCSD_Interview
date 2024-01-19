package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.SkillsFrontend;
import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.domain.UserInfoGithub;
import bcsd.backend.project.pokku.domain.UserPortfolioSkillsFrontend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPortfolioSkillsFrontendRepository extends JpaRepository<UserPortfolioSkillsFrontend, Long> {

    @Query("SELECT count(p) FROM UserPortfolioSkillsFrontend p WHERE p.userInfo = :user_id and p.skillsFrontend = :skills_frontend_id")
    Optional<Long> findEntityCnt(@Param("user_id") UserInfo user, @Param("skills_frontend_id") SkillsFrontend skillsFrontend);

    @Query("SELECT p FROM UserPortfolioSkillsFrontend p WHERE p.userInfo = :user_id and p.skillsFrontend = :skills_frontend_id")
    Optional<UserPortfolioSkillsFrontend> findEntity(@Param("user_id") UserInfo user, @Param("skills_frontend_id") SkillsFrontend skillsFrontend);

}
