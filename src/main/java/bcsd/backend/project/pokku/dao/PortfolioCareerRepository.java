package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioCareerRepository extends JpaRepository<PortfolioCareer, Long> {

    @Query("SELECT s FROM PortfolioCareer s WHERE s.userInfo = :user_id")
    Optional<List<PortfolioCareer>> findByUserId(@Param("user_id") UserInfo userInfo);

    @Query("DELETE FROM PortfolioCareer p WHERE p.userInfo = :user_id and p.careerExplanation = :career_explanation")
    @Modifying
    void deleteByUserIdAndExplanation(@Param("user_id") UserInfo user, @Param("career_explanation") String explanation);

}
