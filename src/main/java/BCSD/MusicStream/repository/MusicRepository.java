package BCSD.MusicStream.repository;

import BCSD.MusicStream.domain.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MusicRepository extends JpaRepository<Music, Long> {

    @Query("select music from Music music join fetch music.user user where music.music_name = ?1")    //This is using a named query method
    List<Music> findMusicByMusicName(String musicName);
}
