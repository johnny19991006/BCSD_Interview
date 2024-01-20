package BCSD.MusicStream.repository;

import BCSD.MusicStream.domain.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayListRepository extends JpaRepository<Playlist, Long> {

}
