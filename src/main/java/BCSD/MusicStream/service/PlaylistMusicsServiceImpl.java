package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.Music;
import BCSD.MusicStream.domain.Playlist;
import BCSD.MusicStream.domain.PlaylistMusic;
import BCSD.MusicStream.dto.AddPlaylistMusicDTO;
import BCSD.MusicStream.repository.MusicRepository;
import BCSD.MusicStream.repository.PlayListRepository;
import BCSD.MusicStream.repository.PlaylistMusicRepository;
import BCSD.MusicStream.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaylistMusicsServiceImpl implements PlaylistMusicsService {
    private final MusicRepository musicRepository;
    private final PlaylistMusicRepository playlistMusicRepository;
    private final PlayListRepository playListRepository;
    private final UserRepository userRepository;

    @Override
    public void addMusic(AddPlaylistMusicDTO addPlaylistMusicDTO) {
        Music music = musicRepository.findById(addPlaylistMusicDTO.getMusic_id().longValue()).orElseThrow(() -> new EntityNotFoundException("Entity not found with music_id " + addPlaylistMusicDTO.getMusic_id()));
        Playlist playlist = playListRepository.findById(addPlaylistMusicDTO.getPlaylist_id().longValue()).orElseThrow(() -> new EntityNotFoundException("Entity not found with playlist_id " + addPlaylistMusicDTO.getPlaylist_id()));
        playlistMusicRepository.save(PlaylistMusic.builder()
                .music(music)
                .playlist(playlist)
                .build());
    }

    @Override
    public void removeMusicByPlaylistMusicId(Integer playlistMusicId) {
        playlistMusicRepository.deleteById(playlistMusicId.longValue());
    }

    @Override
    public List<PlaylistMusic> findAllMusicByPlaylistId(Integer playlistId) {
        return playlistMusicRepository.findAllByPlaylist_id(playlistId);
    }
}
