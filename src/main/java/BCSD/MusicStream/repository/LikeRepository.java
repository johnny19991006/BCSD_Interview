package BCSD.MusicStream.repository;

import BCSD.MusicStream.domain.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<UserLike, Long> {

}
