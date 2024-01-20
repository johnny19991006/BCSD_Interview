package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.Iyrics;
import BCSD.MusicStream.domain.Music;
import BCSD.MusicStream.dto.AddMusicDTO;
import BCSD.MusicStream.dto.MusicDTO;

import java.net.MalformedURLException;
import java.util.List;

public interface MusicService {
    List<MusicDTO> getMusicByMusicNameOrSingerName(String targetText) throws MalformedURLException;
    void addMusic(AddMusicDTO addMusicDTO);
    void modefiedMusic(Integer musicId, MusicDTO musicDTO);
    void deleteMusic(Integer musicId);
    Iyrics getMusicIyrics(Integer musicId);

}
