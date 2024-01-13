package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.UserPortfolioSkillsVersioncontrol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPortfolioSkillsVersioncontrolRepository extends JpaRepository<UserPortfolioSkillsVersioncontrol, Long> {
}
