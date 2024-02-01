package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.Music;
import BCSD.MusicStream.domain.Playlist;
import BCSD.MusicStream.domain.PlaylistMusic;
import BCSD.MusicStream.dto.playlistMusic.AddPlaylistMusicDTO;
import BCSD.MusicStream.dto.playlistMusic.ResponsePlaylistMusicDTO;
import BCSD.MusicStream.exception.CustomException;
import BCSD.MusicStream.exception.ErrorCode;
import BCSD.MusicStream.repository.MusicRepository;
import BCSD.MusicStream.repository.PlayListRepository;
import BCSD.MusicStream.repository.PlaylistMusicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${sound-path}")
    private String musicSoundDir;

    @Value("${image-path}")
    private String musicImageDir;
    @Override
    public AddPlaylistMusicDTO addMusic(AddPlaylistMusicDTO addPlaylistMusicDTO) {
        Music music = musicRepository.findById(addPlaylistMusicDTO.getMusicId()).orElseThrow(() -> new CustomException(ErrorCode.MUSIC_NOT_FOUND));
        Playlist playlist = playListRepository.findById(addPlaylistMusicDTO.getPlaylistId()).orElseThrow(() -> new CustomException(ErrorCode.PLAYLIST_NOT_FOUND));
        playlistMusicRepository.save(PlaylistMusic.builder()
                .music(music)
                .playlist(playlist)
                .build());
        return addPlaylistMusicDTO;
    }

    @Override
    public void deleteMusicByPlaylistMusicId(Integer playlistMusicId) {
        playlistMusicRepository.deleteById(playlistMusicId);
    }

    @Override
    public List<ResponsePlaylistMusicDTO> findAllMusicByPlaylistId(Integer playlistMusicId) {
        List<PlaylistMusic> playlistMusics = playlistMusicRepository.findAllByPlaylistId(playlistMusicId);
        List<ResponsePlaylistMusicDTO> responsePlaylistMusicDTOS = new ArrayList<>(playlistMusics.size());
        for(PlaylistMusic playlistMusic: playlistMusics) {
            Music music = playlistMusic.getMusic();
            responsePlaylistMusicDTOS.add(ResponsePlaylistMusicDTO.builder()
                    .id(playlistMusic.getId())
                    .duration(music.getDuration())
                    .imageFilePath(musicSoundDir + music.getImageFileName())
                    .soundFilePath(musicImageDir + music.getSoundFileName())
                    .musicName(music.getName())
                    .musicId(music.getId())
                    .singerName(music.getSingerName())
                    .build());
        }
        return responsePlaylistMusicDTOS;
    }
}
