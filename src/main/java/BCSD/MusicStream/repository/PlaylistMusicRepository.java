package BCSD.MusicStream.repository;

import BCSD.MusicStream.domain.Music;
import BCSD.MusicStream.domain.PlaylistMusics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistMusicRepository  extends JpaRepository<PlaylistMusics, Long> {
    public List<Music> findAllByPlaylistId(Integer playlistId);
}
