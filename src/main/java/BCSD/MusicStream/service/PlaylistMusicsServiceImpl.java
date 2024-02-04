package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.Music;
import BCSD.MusicStream.domain.Playlist;
import BCSD.MusicStream.domain.PlaylistMusic;
import BCSD.MusicStream.dto.playlistMusic.AddPlaylistMusicDTO;
import BCSD.MusicStream.dto.playlistMusic.ResponsePlaylistMusicDTO;
import BCSD.MusicStream.exception.CustomErrorCodeException;
import BCSD.MusicStream.exception.ErrorCode;
import BCSD.MusicStream.repository.MusicRepository;
import BCSD.MusicStream.repository.PlayListRepository;
import BCSD.MusicStream.repository.PlaylistMusicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional
    @Override
    public AddPlaylistMusicDTO addMusic(AddPlaylistMusicDTO addPlaylistMusicDTO, Integer memberId) {
        Music music = musicRepository.findById(addPlaylistMusicDTO.getMusicId()).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.MUSIC_NOT_FOUND));
        Playlist playlist = playListRepository.findById(addPlaylistMusicDTO.getPlaylistId()).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.PLAYLIST_NOT_FOUND));
        if(playlist.getMember().getId() != memberId) throw new CustomErrorCodeException(ErrorCode.PERMISSION_DENIED_MEMBER);
        playlistMusicRepository.save(PlaylistMusic.builder()
                .music(music)
                .playlist(playlist)
                .build());
        return addPlaylistMusicDTO;
    }
    @Transactional
    @Override
    public void deleteMusicByPlaylistMusicId(Integer playlistMusicId, Integer memberId) {
        PlaylistMusic playlistMusic = playlistMusicRepository.findById(playlistMusicId).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.PLAYLIST_NOT_FOUND));
        if(playlistMusic.getPlaylist().getMember().getId() != memberId) throw new CustomErrorCodeException(ErrorCode.PERMISSION_DENIED_MEMBER);
        playlistMusicRepository.deleteById(playlistMusicId);
    }

    @Override
    public List<ResponsePlaylistMusicDTO> findAllMusicByPlaylistId(Integer playlistId, Integer memberId, Pageable pageable) {
        Playlist playlist = playListRepository.findById(playlistId).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.PLAYLIST_NOT_FOUND));
        if(playlist.getMember().getId() != memberId) throw new CustomErrorCodeException(ErrorCode.PERMISSION_DENIED_MEMBER);
        List<PlaylistMusic> playlistMusics = playlistMusicRepository.findAllByPlaylistId(playlistId, pageable);
        return playlistMusics.stream().map(music -> ResponsePlaylistMusicDTO.builder()
                .id(music.getId())
                .duration(music.getMusic().getDuration())
                .imageFilePath(musicImageDir + music.getMusic().getImageFileName())
                .soundFilePath(musicSoundDir + music.getMusic().getSoundFileName())
                .musicName(music.getMusic().getName())
                .musicId(music.getId())
                .singerName(music.getMusic().getSingerName())
                .build()).collect(Collectors.toList());
    }
}
