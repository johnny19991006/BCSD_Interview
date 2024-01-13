package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.PortfolioArchiving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioArchivingRepository extends JpaRepository<PortfolioArchiving, Long> {
}
