package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.Music;
import BCSD.MusicStream.domain.PlaylistMusics;
import BCSD.MusicStream.dto.AddPlaylistDTO;
import BCSD.MusicStream.dto.AddPlaylistMusicDTO;
import BCSD.MusicStream.dto.ModefiedPlaylistDTO;

import java.util.List;

public interface PlaylistMusicsService {
    public void addMusic(AddPlaylistMusicDTO addPlaylistMusicDTO);
    public void removeMusicByPlaylistMusicId(Integer playlistMusicId);
    public List<PlaylistMusics> findAllMusicByPlaylistId(Integer playlistId);
}
