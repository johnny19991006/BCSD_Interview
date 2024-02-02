package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.domain.UserInfoBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoBlogRepository extends JpaRepository<UserInfoBlog, String> {

    @Query("SELECT p FROM UserInfoBlog p WHERE p.userInfo = :user_id")
    Optional<UserInfoBlog> findByUserId(@Param("user_id") UserInfo user);

    @Query("SELECT COUNT(p) FROM UserInfoBlog p WHERE p.userBlog = :sns")
    Long countById(@Param("sns") String snsName);

    @Query("DELETE FROM UserInfoBlog p WHERE p.userInfo = :user_id")
    @Modifying
    void deleteByUserId(@Param("user_id") UserInfo user);

}
