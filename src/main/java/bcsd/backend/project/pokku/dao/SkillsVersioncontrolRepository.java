package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.SkillsVersioncontrol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsVersioncontrolRepository extends JpaRepository<SkillsVersioncontrol, Long> {
}
