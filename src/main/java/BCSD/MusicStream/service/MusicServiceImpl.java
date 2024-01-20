package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.*;
import BCSD.MusicStream.dto.AddMusicDTO;
import BCSD.MusicStream.dto.MusicDTO;
import BCSD.MusicStream.repository.LikeRepository;
import BCSD.MusicStream.repository.MusicRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MusicServiceImpl implements MusicService{
    private final LikeRepository likeRepository;
    private final MusicRepository musicRepository;
    private final String MUSIC_FILE_PATH = "http://localhost:8080/music/musicMP3/";
    private final String MUSIC_ICON_PATH = "http://localhost:8080/music/musicIcon/";
    @Override
    public List<MusicDTO> getMusicByMusicNameOrSingerName(String musicName) throws MalformedURLException {
        List<Music> musicList = musicRepository.findMusicByMusicNameOrSingerName(musicName);
        List<MusicDTO> musicDTOList = new ArrayList<>(musicList.size());
        for(Music music: musicList) {
            String musicFileUrl = MUSIC_FILE_PATH + music.getMusic_id().toString() + ".MP3";
            String musicIconUrl = MUSIC_ICON_PATH + music.getMusic_id().toString() + ".jpg";
            musicDTOList.add(MusicDTO.builder()
                    .musicId(music.getMusic_id())
                    .musicName(music.getMusic_name())
                    .categoryName(music.getCategory().getCategory_name())
                    .singerName(music.getSinger_name())
                    .userId(music.getUser().getUser_id())
                    .musicTime(music.getMusic_time())
                    .musicFileUrl(musicFileUrl)
                    .musicIconUrl(musicIconUrl)
                    .build());
        }
        return musicDTOList;
    }

    @Override
    public void addMusic(AddMusicDTO addMusicDTO) {
//        Music music = Music.builder()
//                .music_id(null)
//                .music_name(addMusicDTO.getMusicName())
//                .music_time(addMusicDTO.getMusicTime())
//                .category(new Category())

//        Users users = new Users(null,
//                signUpDTO.getUser_name(),
//                signUpDTO.getUser_email(),
//                signUpDTO.getUser_pw(),
//                signUpDTO.getBirth_date(),
//                signUpDTO.getAuthority_type());
//        userRepository.save(users);
    }

    @Override
    public void modefiedMusic(Integer musicId, MusicDTO musicDTO) {

    }

    @Override
    public void deleteMusic(Integer musicId) {

    }

    @Override
    public Iyrics getMusicIyrics(Integer musicId) {
        return null;
    }
}
