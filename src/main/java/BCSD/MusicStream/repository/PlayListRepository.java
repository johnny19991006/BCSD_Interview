package BCSD.MusicStream.repository;

import BCSD.MusicStream.domain.Playlist;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayListRepository extends JpaRepository<Playlist, Integer> {
    public List<Playlist> findAllByMemberId(Integer memberId, Pageable pageable);
}
