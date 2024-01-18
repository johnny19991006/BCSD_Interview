package BCSD.MusicStream.repository;

import BCSD.MusicStream.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    @Query("select user from Users user join fetch user.authority authority where user.user_email = ?1")    //This is using a named query method
    Optional<Users> findByUserEmail(String userEmail);
}
