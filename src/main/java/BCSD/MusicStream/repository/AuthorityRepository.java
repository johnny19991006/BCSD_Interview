package BCSD.MusicStream.repository;

import BCSD.MusicStream.domain.Authority;
import BCSD.MusicStream.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
