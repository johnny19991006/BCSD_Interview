package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.PortfolioCareer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioCareerRepository extends JpaRepository<PortfolioCareer, Long> {
}
