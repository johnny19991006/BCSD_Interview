package BCSD.MusicStream.service;

import BCSD.MusicStream.dto.music.ResponseMusicDTO;
import BCSD.MusicStream.dto.playlistMusic.AddPlaylistMusicDTO;
import BCSD.MusicStream.dto.playlistMusic.ResponsePlaylistMusicDTO;

import java.util.List;

public interface PlaylistMusicsService {
    public AddPlaylistMusicDTO addMusic(AddPlaylistMusicDTO addPlaylistMusicDTO);
    public void deleteMusicByPlaylistMusicId(Integer playlistMusicId);
    public List<ResponsePlaylistMusicDTO> findAllMusicByPlaylistId(Integer playlistId);
}
