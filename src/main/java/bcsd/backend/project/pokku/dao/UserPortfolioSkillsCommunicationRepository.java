package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.UserPortfolioSkillsCommunication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPortfolioSkillsCommunicationRepository extends JpaRepository<UserPortfolioSkillsCommunication, Long> {
}
