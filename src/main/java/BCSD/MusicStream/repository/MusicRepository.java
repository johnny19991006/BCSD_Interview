package BCSD.MusicStream.repository;

import BCSD.MusicStream.domain.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MusicRepository extends JpaRepository<Music, Long> {
    List<Music> findByNameContainingOrSingerNameContaining(String name, String singerName);
}
