package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.SkillsCommunication;
import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.domain.UserPortfolioSkillsCommunication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserPortfolioSkillsCommunicationRepository extends JpaRepository<UserPortfolioSkillsCommunication, Long> {
    @Query("SELECT count(p) FROM UserPortfolioSkillsCommunication p WHERE p.userInfo = :user_id and p.skillsCommunication = :skills_communication_id")
    Optional<Long> findEntityCnt(@Param("user_id") UserInfo user, @Param("skills_communication_id") SkillsCommunication skillsCommunication);

    @Query("SELECT p FROM UserPortfolioSkillsCommunication p WHERE p.userInfo = :user_id and p.skillsCommunication = :skills_communication_id")
    Optional<UserPortfolioSkillsCommunication> findEntity(@Param("user_id") UserInfo user, @Param("skills_communication_id") SkillsCommunication skillsCommunication);

    @Query("SELECT p.skillsCommunication.skillsCommunicationId FROM UserPortfolioSkillsCommunication p WHERE p.userInfo = :user_id")
    List<Long> findByUserId(@Param("user_id") UserInfo user);
}

