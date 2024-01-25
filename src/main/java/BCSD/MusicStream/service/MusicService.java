package BCSD.MusicStream.service;

import BCSD.MusicStream.dto.Lyrics.RequestLyricsDTO;
import BCSD.MusicStream.dto.music.ModifyMusicDTO;
import BCSD.MusicStream.dto.music.RequestMusicDTO;
import BCSD.MusicStream.dto.music.UploadMusicDTO;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface MusicService {
    List<RequestMusicDTO> getMusicByMusicNameOrSingerName(String targetText) throws MalformedURLException;
    RequestLyricsDTO getLyricsByMusicId(Integer musicId);
    Integer addMusic(UploadMusicDTO uploadMusicDTO, Integer memberId) throws IOException, UnsupportedAudioFileException;
    void modifyMusic(ModifyMusicDTO modifyMusicDTO) throws UnsupportedAudioFileException, IOException;
    void deleteMusic(Integer musicId, Integer memberId);
}
