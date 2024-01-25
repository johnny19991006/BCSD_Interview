package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.PlaylistMusic;
import BCSD.MusicStream.dto.playlistMusic.AddPlaylistMusicDTO;
import BCSD.MusicStream.dto.playlistMusic.RequestPlaylistMusicDTO;

import java.util.List;

public interface PlaylistMusicsService {
    public void addMusic(AddPlaylistMusicDTO addPlaylistMusicDTO);
    public void removeMusicByPlaylistMusicId(Integer playlistMusicId);
    public List<RequestPlaylistMusicDTO> findAllMusicByPlaylistId(Integer playlistId);
}
