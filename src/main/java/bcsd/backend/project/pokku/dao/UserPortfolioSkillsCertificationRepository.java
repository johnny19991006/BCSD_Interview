package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.SkillsCertification;
import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.domain.UserPortfolioSkillsCertification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPortfolioSkillsCertificationRepository extends JpaRepository<UserPortfolioSkillsCertification, Long> {

    @Query("SELECT count(p) FROM UserPortfolioSkillsCertification p WHERE p.userInfo = :user_id and p.skillsCertification = :skills_certification_id")
    Optional<Long> findEntityCnt(@Param("user_id") UserInfo user, @Param("skills_certification_id") SkillsCertification skillsCertification);

    @Query("SELECT p FROM UserPortfolioSkillsCertification p WHERE p.userInfo = :user_id and p.skillsCertification = :skills_certification_id")
    Optional<UserPortfolioSkillsCertification> findEntity(@Param("user_id") UserInfo user, @Param("skills_certification_id") SkillsCertification skillsCertification);

    @Query("SELECT p.skillsCertification FROM UserPortfolioSkillsCertification p WHERE p.userInfo = :user_id")
    List<Long> findByUserId(@Param("user_id") UserInfo user);

}
