package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.Image;
import bcsd.backend.project.pokku.domain.PortfolioAbout;
import bcsd.backend.project.pokku.domain.SkillsFrontend;
import bcsd.backend.project.pokku.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillsFrontendRepository extends JpaRepository<SkillsFrontend, Long> {

    @Query("SELECT s FROM SkillsFrontend s WHERE s.image = :skill_name")
    Optional<SkillsFrontend> findBySkillName(@Param("skill_name") Image image);

}
