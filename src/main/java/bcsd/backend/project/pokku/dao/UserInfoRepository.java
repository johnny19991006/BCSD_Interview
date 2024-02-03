package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    @Query("SELECT COUNT(s) FROM UserInfo s WHERE s.userId = :user_id")
    Long countById(@Param("user_id") String userId);
}
