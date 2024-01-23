package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.*;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPortfolioSkillsDeploymentRepository extends JpaRepository<UserPortfolioSkillsDeployment, Long> {
    @Query("SELECT count(p) FROM UserPortfolioSkillsDeployment p WHERE p.userInfo = :user_id and p.skillsDeployment = :skills_deployment_id")
    Optional<Long> findEntityCnt(@Param("user_id") UserInfo user, @Param("skills_deployment_id") SkillsDeployment skillsDeployment);

    @Query("SELECT p FROM UserPortfolioSkillsDeployment p WHERE p.userInfo = :user_id and p.skillsDeployment = :skills_deployment_id")
    Optional<UserPortfolioSkillsDeployment> findEntity(@Param("user_id") UserInfo user, @Param("skills_deployment_id") SkillsDeployment skillsDeployment);

    @Query("SELECT p.skillsDeployment FROM UserPortfolioSkillsDeployment p WHERE p.userInfo = :user_id")
    List<Long> findByUserId(@Param("user_id") UserInfo user);
}
