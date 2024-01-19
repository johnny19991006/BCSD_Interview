package BCSD.MusicStream.repository;

import BCSD.MusicStream.domain.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MusicRepository extends JpaRepository<Music, Long> {
    @Query("SELECT music FROM Music music JOIN FETCH music.user user WHERE music.music_name LIKE %?1% OR music.singer_name LIKE %?1%")
    List<Music> findMusicByMusicNameOrSingerName(String targetText);

}
