package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.Lyric;
import BCSD.MusicStream.dto.AddMusicDTO;
import BCSD.MusicStream.dto.ModefiedMusicDTO;
import BCSD.MusicStream.dto.MusicDTO;
import BCSD.MusicStream.dto.IyricsDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface MusicService {
    List<MusicDTO> getMusicByMusicNameOrSingerName(String targetText) throws MalformedURLException;
    Integer addMusic(AddMusicDTO addMusicDTO) throws IOException;
    void modefiedMusic(ModefiedMusicDTO modefiedMusicDTO);
    void deleteMusic(Integer musicId);
    Lyric getMusicIyrics(Integer musicId);
    void deleteMusicMP3(Integer musicId);
    void deleteMusicIcon(Integer musicId);
    void uploadMusicMP3(Integer musicId, MultipartFile musicMP3) throws IOException;
    void uploadMusicIcon(Integer musicId, MultipartFile musicIcon) throws IOException;
    IyricsDTO getIyrics(Integer musicId) throws IOException;
}
