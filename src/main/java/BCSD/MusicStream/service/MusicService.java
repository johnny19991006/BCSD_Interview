package BCSD.MusicStream.service;

import BCSD.MusicStream.dto.lyrics.ResponseLyricsDTO;
import BCSD.MusicStream.dto.music.ModifyMusicDTO;
import BCSD.MusicStream.dto.music.ResponseMusicDTO;
import BCSD.MusicStream.dto.music.ResponsePlayMusicDTO;
import BCSD.MusicStream.dto.music.UploadMusicDTO;
import org.springframework.data.domain.Pageable;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface MusicService {
    List<ResponseMusicDTO> getMusicByMusicNameOrSingerName(String targetText, Pageable pageable);
    List<ResponseMusicDTO> getAllMusic(Integer memberId, Pageable pageable);
    List<ResponseMusicDTO> getAllMusicByWeather(Integer memberId, String weatherName, Pageable pageable);
    ResponsePlayMusicDTO getLyricsAndLikeByMusicId(Integer musicId, Integer memberId);
    ResponseMusicDTO addMusic(UploadMusicDTO uploadMusicDTO, Integer memberId);
    ResponseMusicDTO modifyMusic(ModifyMusicDTO modifyMusicDTO);
    void deleteMusic(Integer musicId, Integer memberId);
}
