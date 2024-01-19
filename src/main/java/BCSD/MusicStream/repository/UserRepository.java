package BCSD.MusicStream.repository;

import BCSD.MusicStream.domain.Users;
import BCSD.MusicStream.dto.SignUpDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    @Query("select user from Users user where user.user_email = ?1")    //This is using a named query method
    Optional<Users> findByUserEmail(String userEmail);
    @Modifying
    @Transactional
    @Query("DELETE FROM Users user WHERE user.user_email = :email")
    void deleteUserByUserEmail(@Param("email") String userEmail);

}
