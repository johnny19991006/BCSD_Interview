package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillsBackendRepository extends JpaRepository<SkillsBackend, Long> {

    @Query("SELECT s FROM SkillsBackend s WHERE s.image = :skill_name")
    Optional<SkillsBackend> findBySkillName(@Param("skill_name") Image image);

}
