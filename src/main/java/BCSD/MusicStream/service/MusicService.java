package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.Music;

import java.util.List;

public interface MusicService {
    List<Music> getMusicByMusicName(String musicName);
}
