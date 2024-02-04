package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.Image;
import bcsd.backend.project.pokku.domain.SkillsMobileapp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillsMobileappRepository extends JpaRepository<SkillsMobileapp, Long> {

    @Query("SELECT s FROM SkillsMobileapp s WHERE s.image = :skill_name")
    Optional<SkillsMobileapp> findBySkillName(@Param("skill_name") Image image);

}
