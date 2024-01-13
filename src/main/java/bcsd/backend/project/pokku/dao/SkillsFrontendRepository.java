package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.SkillsFrontend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsFrontendRepository extends JpaRepository<SkillsFrontend, Long> {
}
