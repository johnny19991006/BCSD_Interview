package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.UserPortfolioSkillsBackend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPortfolioSkillsBackendRepository extends JpaRepository<UserPortfolioSkillsBackend, Long> {
}
