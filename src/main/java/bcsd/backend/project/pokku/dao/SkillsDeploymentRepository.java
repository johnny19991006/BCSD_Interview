package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.Image;
import bcsd.backend.project.pokku.domain.SkillsDeployment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillsDeploymentRepository extends JpaRepository<SkillsDeployment, Long> {

    @Query("SELECT s FROM SkillsDeployment s WHERE s.image = :skill_name")
    Optional<SkillsDeployment> findBySkillName(@Param("skill_name") Image image);

}
