package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.*;
import BCSD.MusicStream.dto.AddMusicDTO;
import BCSD.MusicStream.dto.ModefiedMusicDTO;
import BCSD.MusicStream.dto.MusicDTO;
import BCSD.MusicStream.dto.IyricsDTO;
import BCSD.MusicStream.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MusicServiceImpl implements MusicService{
    private final LikeRepository likeRepository;
    private final MusicRepository musicRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final IyricsRepository iyricsRepository;
    private final String MUSIC_FILE_PATH = "http://localhost:8080/music/musicMP3/";
    private final String MUSIC_ICON_PATH = "http://localhost:8080/music/musicIcon/";
    private static final String MUSIC_FILE_DIR = "src/main/resources/static/musicMP3/";
    private static final String MUSIC_ICON_DIR = "src/main/resources/static/musicIcon/";
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
                    .categoryName(music.getCategory().getCategoryName())
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
    public Integer addMusic(AddMusicDTO addMusicDTO) throws IOException {
        Category category = categoryRepository.findById(addMusicDTO.getCategoryId().longValue()).orElseThrow(() -> new  EntityNotFoundException("해당 id의 카테고리를 찾지 못했습니다."));
        Users user = userRepository.findById(addMusicDTO.getUserId().longValue()).orElseThrow(() -> new  EntityNotFoundException("해당 id의 유저를 찾지 못했습니다."));
        Music music = Music.builder()
                .music_id(null)
                .music_name(addMusicDTO.getMusicName())
                .music_time(addMusicDTO.getMusicTime())
                .category(category)
                .singer_name(addMusicDTO.getSingerName())
                .music_time(addMusicDTO.getMusicTime())
                .user(user)
                .build();
        Integer musicId = musicRepository.save(music).getMusic_id();
        uploadMusicIcon(musicId, addMusicDTO.getMusicIcon());
        uploadMusicMP3(musicId, addMusicDTO.getMusicFile());
        return musicId;
    }

    @Override
    public void modefiedMusic(ModefiedMusicDTO modefiedMusicDTO) {
        Users user = userRepository.findById(modefiedMusicDTO.getUserId().longValue()).orElseThrow(() -> new EntityNotFoundException("User not found."));
        Category category = categoryRepository.findById(modefiedMusicDTO.getCategoryId().longValue()).orElseThrow(() -> new EntityNotFoundException("Category not found."));
        musicRepository.save(Music.builder()
                .music_id(modefiedMusicDTO.getMusicId())
                .music_name(modefiedMusicDTO.getMusicName())
                .music_time(modefiedMusicDTO.getMusicTime())
                .user(user)
                .category(category)
                .singer_name(modefiedMusicDTO.getSingerName())
                .build());
    }

    @Override
    public void deleteMusic(Integer musicId) {
        musicRepository.deleteById(musicId.longValue());
    }

    @Override
    public Iyrics getMusicIyrics(Integer musicId) {
        return null;
    }

    @Override
    public void deleteMusicMP3(Integer musicId) {
        for(File file: new File(MUSIC_FILE_DIR).listFiles()) {
            if(file.getName().substring(0, file.getName().indexOf('.')).equals(musicId.toString())) file.delete();
        }
    }

    @Override
    public void deleteMusicIcon(Integer musicId) {
        for(File file: new File(MUSIC_ICON_DIR).listFiles()) {
            if(file.getName().substring(0, file.getName().indexOf('.')).equals(musicId.toString())) file.delete();
        }
    }

    @Override
    public void uploadMusicMP3(Integer musicId, MultipartFile musicMP3) throws IOException {
        String musicFileName = musicMP3.getOriginalFilename();
        Path musicFilePath = Paths.get(MUSIC_FILE_DIR + musicId + musicFileName.substring(musicFileName.indexOf('.'), musicFileName.length()));
        Files.copy(musicMP3.getInputStream(), musicFilePath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Override
    public void uploadMusicIcon(Integer musicId, MultipartFile musicIcon) throws IOException {
        String musicIconName = musicIcon.getOriginalFilename();
        Path musicIconPath = Paths.get(MUSIC_ICON_DIR + musicId + musicIconName.substring(musicIconName.indexOf('.'), musicIconName.length()));
        Files.copy(musicIcon.getInputStream(), musicIconPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Override
    public IyricsDTO getIyrics(Integer musicId) throws IOException {
        Iyrics iyrics = iyricsRepository.findById(musicId.longValue()).orElseThrow(() -> new EntityNotFoundException("해당 음악에 대한 가사를 찾을 수 없습니다."));
        return IyricsDTO.builder().iyrics(iyrics.getIyrics_contents()).build();
    }
}
