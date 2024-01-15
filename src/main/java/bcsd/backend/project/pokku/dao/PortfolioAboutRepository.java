package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.PortfolioAbout;
import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.dto.PortfolioAboutRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortfolioAboutRepository extends JpaRepository<PortfolioAbout, Long> {

    @Query("SELECT p FROM PortfolioAbout p WHERE p.userInfo = :user_id")
    Optional<PortfolioAbout> findByUserId(@Param("user_id") UserInfo user);

    @Modifying
    @Query("UPDATE PortfolioAbout p SET p.userEducationVisible = :EducationVisible, p.userEmailVisible = :EmailVisible, p.userNameVisible = :NameVisible, p.userTelVisible = :TelVisible WHERE p.userInfo = :user")
    void updateById(@Param("user") UserInfo user, @Param("EducationVisible") Boolean education, @Param("EmailVisible") Boolean email, @Param("NameVisible") Boolean name, @Param("TelVisible") Boolean tel);
}
