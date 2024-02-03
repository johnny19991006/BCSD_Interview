package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.Image;
import bcsd.backend.project.pokku.domain.SkillsVersioncontrol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillsVersioncontrolRepository extends JpaRepository<SkillsVersioncontrol, Long> {
    @Query("SELECT s FROM SkillsVersioncontrol s WHERE s.image = :skill_name")
    Optional<SkillsVersioncontrol> findBySkillName(@Param("skill_name") Image image);
}
