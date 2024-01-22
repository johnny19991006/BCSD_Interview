package BCSD.MusicStream.repository;

import BCSD.MusicStream.domain.Lyric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IyricsRepository extends JpaRepository<Lyric, Long> {

}
