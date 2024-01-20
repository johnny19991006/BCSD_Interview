package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.Music;
import BCSD.MusicStream.domain.Playlist;
import BCSD.MusicStream.domain.PlaylistMusics;
import BCSD.MusicStream.domain.Users;
import BCSD.MusicStream.dto.AddPlaylistDTO;
import BCSD.MusicStream.dto.ModefiedPlaylistDTO;
import BCSD.MusicStream.repository.MusicRepository;
import BCSD.MusicStream.repository.PlayListRepository;
import BCSD.MusicStream.repository.PlaylistMusicRepository;
import BCSD.MusicStream.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService{
    private final MusicRepository musicRepository;
    private final PlaylistMusicRepository playlistMusicRepository;
    private final PlayListRepository playListRepository;
    private final UserRepository userRepository;
    @Override
    public void addPlaylist(AddPlaylistDTO playlistDTO) {
        Users user = userRepository.findById(playlistDTO.getUser_id().longValue()).get();
        playListRepository.save(Playlist.builder()
                .playlist_name(playlistDTO.getPlaylist_name())
                .user(user)
                .build());
    }

    @Override
    public void removePlaylist(Integer playlistId) {
        playListRepository.deleteById(playlistId.longValue());
    }

    @Override
    public void modefiedPlaylistName(ModefiedPlaylistDTO modefiedPlaylistDTO) {
        Playlist playlist = playListRepository.findById(modefiedPlaylistDTO.getPlaylist_id().longValue()).orElseThrow(() -> new EntityNotFoundException("Entity not found with id " + modefiedPlaylistDTO.getPlaylist_id()));
        playlist.setPlaylist_name(modefiedPlaylistDTO.getPlaylist_name());
        playListRepository.save(playlist);
    }

    @Override
    public void addPlaylistMusic(Integer playlistId, Integer musicId) {
        Playlist playlist = playListRepository.findById(playlistId.longValue()).get();
        Music music = musicRepository.findById(musicId.longValue()).get();
        playlistMusicRepository.save(PlaylistMusics.builder().music(music).playlist(playlist).build());
    }
}
