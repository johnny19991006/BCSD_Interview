package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    @Query("SELECT COUNT(s) FROM Image s WHERE s.skillName = :name")
    Long countByName(@Param("name") String name);
}
