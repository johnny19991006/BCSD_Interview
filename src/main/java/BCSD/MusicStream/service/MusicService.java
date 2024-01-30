package BCSD.MusicStream.service;

import BCSD.MusicStream.dto.lyrics.RequestLyricsDTO;
import BCSD.MusicStream.dto.music.ModifyMusicDTO;
import BCSD.MusicStream.dto.music.RequestMusicDTO;
import BCSD.MusicStream.dto.music.UploadMusicDTO;
import org.springframework.data.domain.Pageable;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface MusicService {
    List<RequestMusicDTO> getMusicByMusicNameOrSingerName(String targetText) throws MalformedURLException;
    List<RequestMusicDTO> getAllMusic(Long memberId, Pageable pageable);
    List<RequestMusicDTO> getAllMusicByWeather(Long memberId, String weatherName, Pageable pageable);
    RequestLyricsDTO getLyricsByMusicId(Integer musicId);
    Integer addMusic(UploadMusicDTO uploadMusicDTO, Integer memberId) throws IOException, UnsupportedAudioFileException;
    ModifyMusicDTO modifyMusic(ModifyMusicDTO modifyMusicDTO) throws UnsupportedAudioFileException, IOException;
    void deleteMusic(Integer musicId, Integer memberId);
}
