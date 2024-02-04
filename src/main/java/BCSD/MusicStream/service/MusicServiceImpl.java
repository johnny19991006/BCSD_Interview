package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.*;
import BCSD.MusicStream.dto.music.*;
import BCSD.MusicStream.exception.CustomErrorCodeException;
import BCSD.MusicStream.exception.ErrorCode;
import BCSD.MusicStream.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Time;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MusicServiceImpl implements MusicService{
    private final MusicRepository musicRepository;
    private final LyricsRepository lyricsRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;
    private final WeatherRepository weatherRepository;
    private final LikeRepository likeRepository;

    @Value("${sound-path}")
    private String musicSoundDir;

    @Value("${image-path}")
    private String musicImageDir;

    @Override
    public List<ResponseMusicDTO> getMusicByMusicNameOrSingerName(String targetText, Pageable pageable) {
        List<Music> musicList = musicRepository.findByNameContainingOrSingerNameContaining(targetText, targetText, pageable);
        return musicList.stream().map(music -> ResponseMusicDTO.builder()
                        .id(music.getId())
                        .name(music.getName())
                        .singerName(music.getSingerName())
                        .duration(music.getDuration())
                        .imageFilePath("/musicImage/" + music.getImageFileName())
                        .soundFilePath("/musicSound/" + music.getSoundFileName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ResponsePlayMusicDTO getLyricsAndLikeByMusicId(Integer musicId, Integer memberId) {
        Lyrics lyrics = lyricsRepository.findById(musicId).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.LYRICS_NOT_FOUND));
        Boolean isLike = likeRepository.findLikeByMusicIdAndMemberId(musicId, memberId).map(Like::getIsLike).orElse(null);
        return ResponsePlayMusicDTO.builder()
                .lyrics(lyrics.getContents())
                .isLike(isLike)
                .build();
    }

    @Transactional
    @Override
    public ResponseMusicDTO addMusic(UploadMusicDTO uploadMusicDTO, Integer memberId) {
        Category category = categoryRepository.findById(uploadMusicDTO.getCategoryId()).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.CATEGORY_NOT_FOUND));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.MEMBER_NOT_FOUND));
        Weather weather = weatherRepository.findById(uploadMusicDTO.getWeatherId()).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.WEATHER_NOT_FOUND));

        Music music = musicRepository.save(Music.builder()
                .name(uploadMusicDTO.getName())
                .singerName(uploadMusicDTO.getSingerName())
                .category(category)
                .member(member)
                .duration(uploadMusicDTO.getDuration())
                .weather(weather)
                .build());

        String soundFileName = generateFileName(music.getId(), uploadMusicDTO.getSoundFile().getOriginalFilename());
        String imageFileName = generateFileName(music.getId(), uploadMusicDTO.getImageFile().getOriginalFilename());
        music.setFilesName(soundFileName, imageFileName);

        try {
            saveFile(uploadMusicDTO.getSoundFile(), Paths.get(musicSoundDir + soundFileName));
            saveFile(uploadMusicDTO.getImageFile(), Paths.get(musicImageDir + imageFileName));
        }catch (IOException e) {
            throw new CustomErrorCodeException(ErrorCode.FILE_FAILD);
        }

        musicRepository.save(music);

        lyricsRepository.save(Lyrics.builder()
                .musicId(music.getId())
                .contents(uploadMusicDTO.getLyrics())
                .build());

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
    public ResponseMusicDTO modifyMusic(ModifyMusicDTO modifyMusicDTO, Integer memberId) {
        Music music = musicRepository.findById(modifyMusicDTO.getId()).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.MUSIC_NOT_FOUND));
        if(music.getMember().getId() != memberId) throw new CustomErrorCodeException(ErrorCode.PERMISSION_DENIED_MEMBER);
        Lyrics lyrics = lyricsRepository.findById(modifyMusicDTO.getId()).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.LYRICS_NOT_FOUND));
        Category category = categoryRepository.findById(modifyMusicDTO.getCategoryId()).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.CATEGORY_NOT_FOUND));
        Weather weather = weatherRepository.findById(modifyMusicDTO.getWeatherId()).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.WEATHER_NOT_FOUND));

        String soundFileName = generateFileName(music.getId(), modifyMusicDTO.getSoundFile().getOriginalFilename());
        String imageFileName = generateFileName(music.getId(), modifyMusicDTO.getImageFile().getOriginalFilename());

        try {
            deleteFile(musicSoundDir + music.getSoundFileName());
            deleteFile(musicImageDir + music.getImageFileName());
            saveFile(modifyMusicDTO.getSoundFile(), Paths.get(musicSoundDir + soundFileName));
            saveFile(modifyMusicDTO.getImageFile(), Paths.get(musicImageDir + imageFileName));
        } catch (IOException e) {
            throw new CustomErrorCodeException(ErrorCode.FILE_FAILD);
        }

        music.setName(modifyMusicDTO.getName());
        music.setSingerName(modifyMusicDTO.getSingerName());
        music.setCategory(category);
        music.setWeather(weather);
        music.setDuration(Time.valueOf(modifyMusicDTO.getDuration()));

        lyrics.setContents(modifyMusicDTO.getLyrics());

        return ResponseMusicDTO.builder()
                .singerName(music.getSingerName())
                .soundFilePath(musicSoundDir + music.getSoundFileName())
                .imageFilePath(musicImageDir + music.getImageFileName())
                .duration(music.getDuration())
                .id(music.getId())
                .name(music.getName())
                .build();
    }

    @Override
    public MusicInfoDTO getMusicInfo(Integer musicId, Integer memberId) {
        Music music = musicRepository.findById(musicId).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.MUSIC_NOT_FOUND));
        if(music.getMember().getId() != memberId) throw new CustomErrorCodeException(ErrorCode.PERMISSION_DENIED_MEMBER);
        Lyrics lyrics = lyricsRepository.findById(musicId).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.LYRICS_NOT_FOUND));
        return MusicInfoDTO.builder()
                .id(music.getId())
                .categoryId(music.getCategory().getId())
                .imageFilePath(musicImageDir + music.getImageFileName())
                .soundFilePath(musicSoundDir + music.getSoundFileName())
                .lyrics(lyrics.getContents())
                .weatherId(music.getWeather().getId())
                .singerName(music.getSingerName())
                .duration(String.valueOf(music.getDuration()))
                .name(music.getName())
                .build();
    }

    @Transactional
    @Override
    public void deleteMusic(Integer musicId, Integer memberId) {
        Music music = musicRepository.findById(musicId).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.MUSIC_NOT_FOUND));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.MEMBER_NOT_FOUND));
        if (!music.getMember().equals(member)) throw new CustomErrorCodeException(ErrorCode.PERMISSION_DENIED_MUSIC);
        try {
            deleteFile(musicSoundDir + music.getSoundFileName());
            deleteFile(musicImageDir + music.getImageFileName());
        } catch (IOException e) {
            throw new CustomErrorCodeException(ErrorCode.FILE_FAILD);
        }
        musicRepository.delete(music);
    }

    @Override
    public List<ResponseMusicDTO> getAllMusic(Integer memberId, Pageable pageable) {
        List<Music> musicList = musicRepository.findMusicWithWeight(memberId, pageable);
        return musicList.stream().map(music -> ResponseMusicDTO.builder()
                        .id(music.getId())
                        .name(music.getName())
                        .singerName(music.getSingerName())
                        .duration(music.getDuration())
                        .imageFilePath("/musicImage/" + music.getImageFileName())
                        .soundFilePath("/musicSound/" + music.getSoundFileName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ResponseMusicDTO> getAllMusicByWeather(Integer memberId, String weather, Pageable pageable) {
        Integer weatherId = weatherRepository.findWeatherByName(weather).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.WEATHER_NOT_FOUND)).getId();
        List<Music> musicList = musicRepository.findMusicByWeatherWithWeight(memberId, weatherId, pageable);
        return musicList.stream().map(music -> ResponseMusicDTO.builder()
                        .id(music.getId())
                        .name(music.getName())
                        .singerName(music.getSingerName())
                        .duration(music.getDuration())
                        .imageFilePath("/musicImage/" + music.getImageFileName())
                        .soundFilePath("/musicSound/" + music.getSoundFileName())
                        .build())
                .collect(Collectors.toList());
    }
    private void deleteFile(String path) throws IOException {
        File file = new File(path);
        file.delete();
    }
    private void saveFile(MultipartFile file, Path destination) throws IOException {
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
    }
    private String generateFileName(Integer id, String originalFilename) {
        String extension = Objects.requireNonNull(originalFilename).substring(originalFilename.lastIndexOf('.'));
        return id + extension;
    }
}
