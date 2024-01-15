package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.PortfolioAbout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortfolioAboutRepository extends JpaRepository<PortfolioAbout, Long> {

    @Query("SELECT p FROM PortfolioAbout p WHERE p.userInfo = :user_id")
    Optional<PortfolioAbout> findByUserId(@Param("user_id") String user_id);
}
