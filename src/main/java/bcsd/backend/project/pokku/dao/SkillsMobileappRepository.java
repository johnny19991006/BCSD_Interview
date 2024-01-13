package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.SkillsMobileapp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsMobileappRepository extends JpaRepository<SkillsMobileapp, Long> {
}
