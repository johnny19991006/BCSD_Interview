package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.Music;
import BCSD.MusicStream.domain.Playlist;
import BCSD.MusicStream.domain.PlaylistMusic;
import BCSD.MusicStream.dto.playlist.RequestPlaylistDTO;
import BCSD.MusicStream.dto.playlistMusic.AddPlaylistMusicDTO;
import BCSD.MusicStream.dto.playlistMusic.RequestPlaylistMusicDTO;
import BCSD.MusicStream.repository.MusicRepository;
import BCSD.MusicStream.repository.PlayListRepository;
import BCSD.MusicStream.repository.PlaylistMusicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaylistMusicsServiceImpl implements PlaylistMusicsService {
    private final MusicRepository musicRepository;
    private final PlaylistMusicRepository playlistMusicRepository;
    private final PlayListRepository playListRepository;
    private static final String MUSIC_SOUND_DIR = "src/main/resources/static/musicSound/";
    private static final String MUSIC_IMAGE_DIR = "src/main/resources/static/musicImage/";
    @Override
    public void addMusic(AddPlaylistMusicDTO addPlaylistMusicDTO) {
        Music music = musicRepository.findById(addPlaylistMusicDTO.getMusicId().longValue()).orElseThrow(() -> new EntityNotFoundException("Entity not found with music_id " + addPlaylistMusicDTO.getMusicId()));
        Playlist playlist = playListRepository.findById(addPlaylistMusicDTO.getPlaylistId().longValue()).orElseThrow(() -> new EntityNotFoundException("Entity not found with playlist_id " + addPlaylistMusicDTO.getPlaylistId()));
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
    public List<RequestPlaylistMusicDTO> findAllMusicByPlaylistId(Integer playlistMusicId) {
        List<PlaylistMusic> playlistMusics = playlistMusicRepository.findAllByPlaylistId(playlistMusicId);
        List<RequestPlaylistMusicDTO> requestPlaylistMusicDTOS = new ArrayList<>(playlistMusics.size());
        for(PlaylistMusic playlistMusic: playlistMusics) {
            Music music = playlistMusic.getMusic();
            requestPlaylistMusicDTOS.add(RequestPlaylistMusicDTO.builder()
                    .id(playlistMusic.getId())
                    .duration(music.getDuration())
                    .imageFilePath(MUSIC_IMAGE_DIR + music.getImageFileName())
                    .soundFilePath(MUSIC_SOUND_DIR + music.getSoundFileName())
                    .musicName(music.getName())
                    .musicId(music.getId())
                    .singerName(music.getSingerName())
                    .build());
        }
        return requestPlaylistMusicDTOS;
    }
}
