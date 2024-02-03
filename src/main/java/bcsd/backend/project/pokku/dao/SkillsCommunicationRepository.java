package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.Image;
import bcsd.backend.project.pokku.domain.SkillsCommunication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillsCommunicationRepository extends JpaRepository<SkillsCommunication, Long> {

    @Query("SELECT s FROM SkillsCommunication s WHERE s.image = :skill_name")
    Optional<SkillsCommunication> findBySkillName(@Param("skill_name") Image image);

}
