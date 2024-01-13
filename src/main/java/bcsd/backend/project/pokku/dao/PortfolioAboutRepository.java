package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.PortfolioAbout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioAboutRepository extends JpaRepository<PortfolioAbout, Long> {
}
