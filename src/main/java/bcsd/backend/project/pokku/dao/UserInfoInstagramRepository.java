package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.domain.UserInfoInstagram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoInstagramRepository extends JpaRepository<UserInfoInstagram, String> {

    @Query("SELECT p FROM UserInfoInstagram p WHERE p.userInfo = :user_id")
    Optional<UserInfoInstagram> findByUserId(@Param("user_id") UserInfo user);

}
