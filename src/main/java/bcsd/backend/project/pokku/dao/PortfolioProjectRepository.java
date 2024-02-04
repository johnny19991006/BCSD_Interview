package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.PortfolioProject;
import bcsd.backend.project.pokku.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioProjectRepository extends JpaRepository<PortfolioProject, Long> {

    @Query("SELECT s FROM PortfolioProject s WHERE s.userInfo = :user_id")
    Optional<List<PortfolioProject>> findByUserId(@Param("user_id") UserInfo userInfo);

    @Query("DELETE FROM PortfolioProject p WHERE p.userInfo = :user_id and p.projectName = :project_name")
    @Modifying
    void deleteByUserIdAndName(@Param("user_id") UserInfo user, @Param("project_name") String name);

}
