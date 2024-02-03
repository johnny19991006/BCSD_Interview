package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.PortfolioArchiving;
import bcsd.backend.project.pokku.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioArchivingRepository extends JpaRepository<PortfolioArchiving, Long> {

    @Query("SELECT s FROM PortfolioArchiving s WHERE s.userInfo = :user_id")
    Optional<List<PortfolioArchiving>> findByUserId(@Param("user_id") UserInfo userInfo);

    @Query("DELETE FROM PortfolioArchiving p WHERE p.userInfo = :user_id and p.archivingName = :archiving_name")
    @Modifying
    void deleteByUserIdAndName(@Param("user_id") UserInfo user, @Param("archiving_name") String name);

}
