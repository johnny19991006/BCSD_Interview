package BCSD.MusicStream.repository;

import BCSD.MusicStream.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    Optional<Like> findLikeByIdAndMemberId(Integer musicId, Integer memberId);
}
