package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.PlaylistMusic;
import BCSD.MusicStream.dto.AddPlaylistMusicDTO;

import java.util.List;

public interface PlaylistMusicsService {
    public void addMusic(AddPlaylistMusicDTO addPlaylistMusicDTO);
    public void removeMusicByPlaylistMusicId(Integer playlistMusicId);
    public List<PlaylistMusic> findAllMusicByPlaylistId(Integer playlistId);
}
