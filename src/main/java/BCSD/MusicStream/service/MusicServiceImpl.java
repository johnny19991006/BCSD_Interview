package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.Music;
import BCSD.MusicStream.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService{

    private final MusicRepository musicRepository;
    @Autowired
    public MusicServiceImpl(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }
    @Override
    public List<Music> getMusicByMusicName(String musicName) {
        return musicRepository.findMusicByMusicName(musicName);
    }
}
