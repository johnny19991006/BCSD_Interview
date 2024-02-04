package HSAnimal.demo.repository;

import HSAnimal.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
    List<User> findAllBy();
    Boolean existsByUserId(String userId);
    Boolean existsByEmail(String Email);
}

