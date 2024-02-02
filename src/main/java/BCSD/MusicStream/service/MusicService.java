package BCSD.MusicStream.service;

import BCSD.MusicStream.dto.lyrics.ResponseLyricsDTO;
import BCSD.MusicStream.dto.music.ModifyMusicDTO;
import BCSD.MusicStream.dto.music.ResponseMusicDTO;
import BCSD.MusicStream.dto.music.UploadMusicDTO;
import org.springframework.data.domain.Pageable;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface MusicService {
    List<ResponseMusicDTO> getMusicByMusicNameOrSingerName(String targetText, Pageable pageable) throws MalformedURLException;
    List<ResponseMusicDTO> getAllMusic(Integer memberId, Pageable pageable);
    List<ResponseMusicDTO> getAllMusicByWeather(Integer memberId, String weatherName, Pageable pageable);
    ResponseLyricsDTO getLyricsByMusicId(Integer musicId);
    ResponseMusicDTO addMusic(UploadMusicDTO uploadMusicDTO, Integer memberId) throws IOException, UnsupportedAudioFileException;
    ResponseMusicDTO modifyMusic(ModifyMusicDTO modifyMusicDTO) throws UnsupportedAudioFileException, IOException;
    void deleteMusic(Integer musicId, Integer memberId) throws IOException;
}
