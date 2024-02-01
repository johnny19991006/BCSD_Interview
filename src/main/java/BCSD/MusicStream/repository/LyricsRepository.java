package BCSD.MusicStream.repository;

import BCSD.MusicStream.domain.Lyrics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LyricsRepository extends JpaRepository<Lyrics, Integer> {

}
