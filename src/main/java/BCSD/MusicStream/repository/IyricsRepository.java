package BCSD.MusicStream.repository;

import BCSD.MusicStream.domain.Category;
import BCSD.MusicStream.domain.Iyrics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IyricsRepository extends JpaRepository<Iyrics, Long> {

}
