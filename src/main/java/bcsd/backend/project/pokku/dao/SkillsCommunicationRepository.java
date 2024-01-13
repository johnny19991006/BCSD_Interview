package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.SkillsCommunication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsCommunicationRepository extends JpaRepository<SkillsCommunication, Long> {
}
