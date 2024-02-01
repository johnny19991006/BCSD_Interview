package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.*;
import BCSD.MusicStream.dto.lyrics.ResponseLyricsDTO;
import BCSD.MusicStream.dto.music.ModifyMusicDTO;
import BCSD.MusicStream.dto.music.ResponseMusicDTO;
import BCSD.MusicStream.dto.music.UploadMusicDTO;
import BCSD.MusicStream.exception.CustomException;
import BCSD.MusicStream.exception.ErrorCode;
import BCSD.MusicStream.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
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
    private final MusicRepository musicRepository;
    private final LyricsRepository lyricsRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;
    private final WeatherRepository weatherRepository;

    @Value("${sound-path}")
    private String musicSoundDir;

    @Value("${image-path}")
    private String musicImageDir;

    @Override
    public List<ResponseMusicDTO> getMusicByMusicNameOrSingerName(String targetText) {
        List<Music> musicList = musicRepository.findByNameContainingOrSingerNameContaining(targetText, targetText);
        List<ResponseMusicDTO> musicDTOList = new ArrayList<>(musicList.size());
        for(Music music: musicList) {
            String musicSoundUrl = musicSoundDir + music.getSoundFileName();
            String musicImageUrl = musicImageDir + music.getImageFileName();
            musicDTOList.add(ResponseMusicDTO.builder()
                    .id(music.getId())
                    .name(music.getName())
                    .singerName(music.getSingerName())
                    .duration(music.getDuration())
                    .imageFilePath(musicImageUrl)
                    .soundFilePath(musicSoundUrl)
                    .build());
        }
        return musicDTOList;
    }

    @Override
    public ResponseLyricsDTO getLyricsByMusicId(Integer musicId) {
        Lyrics lyrics = lyricsRepository.findById(musicId).orElseThrow(() -> new CustomException(ErrorCode.LYRICS_NOT_FOUND));
        return ResponseLyricsDTO.builder()
                .lyrics(lyrics.getContents())
                .build();
    }

    @Transactional
    @Override
    public ResponseMusicDTO addMusic(UploadMusicDTO uploadMusicDTO, Integer memberId) throws IOException {
        Category category = categoryRepository.findById(uploadMusicDTO.getCategoryId()).orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Weather weather = weatherRepository.findById(uploadMusicDTO.getWeatherId()).orElseThrow(() -> new CustomException(ErrorCode.WEATHER_NOT_FOUND));
        if(uploadMusicDTO.getSoundFile().isEmpty()) throw new CustomException(ErrorCode.SOUND_FILE_NOT_CONTAIN);
        if(uploadMusicDTO.getImageFile().isEmpty()) throw new CustomException(ErrorCode.IMAGE_FILE_NOT_CONTAIN);
        Music music  = Music.builder()
                .name(uploadMusicDTO.getName())
                .singerName(uploadMusicDTO.getSingerName())
                .category(category)
                .member(member)
                .duration(uploadMusicDTO.getDuration())
                .imageFileName("")
                .soundFileName("")
                .weather(weather)
                .build();
        Integer musicId = musicRepository.save(music).getId();
        String soundFileName = uploadMusicDTO.getSoundFile().getOriginalFilename();
        String imageFileName = uploadMusicDTO.getImageFile().getOriginalFilename();
        soundFileName = musicId + soundFileName.substring(soundFileName.indexOf('.'), soundFileName.length());
        imageFileName = musicId + imageFileName.substring(imageFileName.indexOf('.'), imageFileName.length());
        music.setId(musicId);
        music.setImageFileName(imageFileName);
        music.setSoundFileName(soundFileName);
        saveFile(uploadMusicDTO.getSoundFile(), Paths.get(musicSoundDir + soundFileName));
        saveFile(uploadMusicDTO.getSoundFile(), Paths.get(musicImageDir + imageFileName));
        musicRepository.save(music);
        lyricsRepository.save(Lyrics.builder().contents(uploadMusicDTO.getLyrics()).build());
        return ResponseMusicDTO.builder()
                .singerName(music.getSingerName())
                .soundFilePath(musicSoundDir + music.getSoundFileName())
                .imageFilePath(musicImageDir + music.getImageFileName())
                .duration(music.getDuration())
                .id(music.getId())
                .name(music.getName())
                .build();
    }

    @Transactional
    @Override
    public ResponseMusicDTO modifyMusic(ModifyMusicDTO modifyMusicDTO) throws UnsupportedAudioFileException, IOException {
        Music music = musicRepository.findById(modifyMusicDTO.getId()).orElseThrow(() -> new CustomException(ErrorCode.MUSIC_NOT_FOUND));
        Lyrics lyrics = lyricsRepository.findById(modifyMusicDTO.getId()).orElseThrow(() -> new CustomException(ErrorCode.LYRICS_NOT_FOUND));
        Category category = categoryRepository.findById(modifyMusicDTO.getCategoryId()).orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));
        Weather weather = weatherRepository.findById(modifyMusicDTO.getWeatherId()).orElseThrow(() -> new CustomException(ErrorCode.WEATHER_NOT_FOUND));
        if (modifyMusicDTO.getSoundFile().isEmpty()) throw new CustomException(ErrorCode.SOUND_FILE_NOT_CONTAIN);
        if (modifyMusicDTO.getImageFile().isEmpty()) throw new CustomException(ErrorCode.IMAGE_FILE_NOT_CONTAIN);
        if (modifyMusicDTO.getName().isBlank()) throw new CustomException(ErrorCode.NAME_BLANK);
        if (modifyMusicDTO.getLyrics().isBlank()) throw new CustomException(ErrorCode.LYRICS_BLANK);
        if (modifyMusicDTO.getSingerName().isBlank()) throw new CustomException(ErrorCode.SINGER_NAME_BLANK);
        String soundFileName = modifyMusicDTO.getSoundFile().getName();
        String imageFileName = modifyMusicDTO.getImageFile().getName();
        soundFileName = modifyMusicDTO.getId() + soundFileName.substring(soundFileName.indexOf('.'), soundFileName.length());
        imageFileName = modifyMusicDTO.getId() + imageFileName.substring(imageFileName.indexOf('.'), imageFileName.length());
        music.setName(modifyMusicDTO.getName());
        music.setCategory(category);
        music.setSingerName(modifyMusicDTO.getSingerName());
        music.setWeather(weather);

        deleteFile(musicSoundDir + music.getSoundFileName());
        deleteFile(musicImageDir + music.getImageFileName());

        music.setSoundFileName(soundFileName);
        music.setImageFileName(imageFileName);

        lyrics.setContents(modifyMusicDTO.getLyrics());

        saveFile(modifyMusicDTO.getSoundFile(), Paths.get(musicSoundDir + soundFileName));
        saveFile(modifyMusicDTO.getImageFile(), Paths.get(musicImageDir + imageFileName));

        musicRepository.save(music);
        lyricsRepository.save(lyrics);
        return ResponseMusicDTO.builder()
                .singerName(music.getSingerName())
                .soundFilePath(musicSoundDir + music.getSoundFileName())
                .imageFilePath(musicImageDir + music.getImageFileName())
                .duration(music.getDuration())
                .id(music.getId())
                .name(music.getName())
                .build();
    }

    @Transactional
    @Override
    public void deleteMusic(Integer musicId, Integer memberId) throws IOException {
        Music music = musicRepository.findById(musicId).orElseThrow(() -> new CustomException(ErrorCode.MUSIC_NOT_FOUND));
        if(music.getMember().getId() != memberId) throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
        deleteFile(musicSoundDir + music.getSoundFileName());
        deleteFile(musicImageDir + music.getImageFileName());
    }

    @Override
    public List<ResponseMusicDTO> getAllMusic(Integer memberId, Pageable pageable) {
        List<Music> musicList = musicRepository.findMusicWithWeight(memberId, pageable);
        List<ResponseMusicDTO> requestMusicDTOList = new ArrayList<>(musicList.size());
        for(Music music: musicList) {
            requestMusicDTOList.add(ResponseMusicDTO.builder()
                    .id(music.getId())
                    .soundFilePath(music.getSoundFileName())
                    .imageFilePath(music.getImageFileName())
                    .duration(music.getDuration())
                    .name(music.getName())
                    .singerName(music.getSingerName())
                    .build()
            );
        }
        return requestMusicDTOList;
    }

    @Override
    public List<ResponseMusicDTO> getAllMusicByWeather(Integer memberId, String weather, Pageable pageable) {
        Integer weatherId = weatherRepository.findWeatherByName(weather).orElseThrow(() -> new CustomException(ErrorCode.WEATHER_NOT_FOUND)).getId();
        List<Music> musicList = musicRepository.findMusicByWeatherWithWeight(memberId, weatherId, pageable);
        List<ResponseMusicDTO> requestMusicDTOList = new ArrayList<>(musicList.size());
        for(Music music: musicList) {
            requestMusicDTOList.add(ResponseMusicDTO.builder()
                    .id(music.getId())
                    .soundFilePath(music.getSoundFileName())
                    .imageFilePath(music.getImageFileName())
                    .duration(music.getDuration())
                    .name(music.getName())
                    .singerName(music.getSingerName())
                    .build()
            );
        }
        return requestMusicDTOList;
    }
    private void deleteFile(String path) throws IOException {
        File file = new File(path);
        file.delete();
    }
    private void saveFile(MultipartFile file, Path destination) throws IOException {
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
    }
    private String generateFileName(String originalName, Integer id) {
        return id + originalName.substring(originalName.lastIndexOf('.'));
    }
}
