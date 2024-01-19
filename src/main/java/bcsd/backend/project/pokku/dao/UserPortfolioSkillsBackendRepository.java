package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPortfolioSkillsBackendRepository extends JpaRepository<UserPortfolioSkillsBackend, Long> {

    @Query("SELECT count(p) FROM UserPortfolioSkillsBackend p WHERE p.userInfo = :user_id and p.skillsBackend = :skills_backend_id")
    Optional<Long> findEntityCnt(@Param("user_id") UserInfo user, @Param("skills_backend_id") SkillsBackend skillsBackend);

    @Query("SELECT p FROM UserPortfolioSkillsBackend p WHERE p.userInfo = :user_id and p.skillsBackend = :skills_backend_id")
    Optional<UserPortfolioSkillsBackend> findEntity(@Param("user_id") UserInfo user, @Param("skills_backend_id") SkillsBackend skillsBackend);

}
