package bcsd.backend.project.pokku.dao;

import bcsd.backend.project.pokku.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
