package BCSD.MusicStream.repository;

import BCSD.MusicStream.domain.PlaylistMusic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistMusicRepository  extends JpaRepository<PlaylistMusic, Integer> {
    public List<PlaylistMusic> findAllByPlaylistId(Integer playlistId);
}
