package BCSD.MusicStream.service;

import BCSD.MusicStream.dto.music.ResponseMusicDTO;
import BCSD.MusicStream.dto.playlistMusic.AddPlaylistMusicDTO;
import BCSD.MusicStream.dto.playlistMusic.ResponsePlaylistMusicDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlaylistMusicsService {
    public AddPlaylistMusicDTO addMusic(AddPlaylistMusicDTO addPlaylistMusicDTO, Integer memberId);
    public void deleteMusicByPlaylistMusicId(Integer playlistMusicId, Integer memberId);
    public List<ResponsePlaylistMusicDTO> findAllMusicByPlaylistId(Integer playlistId, Integer memberId, Pageable pageable);
}
