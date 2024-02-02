package BCSD.MusicStream.controller;

import BCSD.MusicStream.api.OpenWeather;
import BCSD.MusicStream.api.WeatherAPI;
import BCSD.MusicStream.config.WebConfig;
import BCSD.MusicStream.dto.lyrics.ResponseLyricsDTO;
import BCSD.MusicStream.dto.music.ModifyMusicDTO;
import BCSD.MusicStream.dto.music.ResponseMusicDTO;
import BCSD.MusicStream.dto.music.UploadMusicDTO;
import BCSD.MusicStream.service.GeoService;
import BCSD.MusicStream.service.LikeService;
import BCSD.MusicStream.service.MusicService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Location;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/music")
@AllArgsConstructor
public class MusicController {
    private final MusicService musicService;
    private final LikeService likeService;
    private final GeoService geoService;
    private final WeatherAPI weatherAPI;
    @GetMapping
    public ResponseEntity<List<ResponseMusicDTO>> getAllMusic(HttpServletRequest request, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(musicService.getAllMusic(WebConfig.getMemberIdByRequest(request), pageable));
    }
    @GetMapping("/{targetText}")
    public ResponseEntity<List<ResponseMusicDTO>> getMusicByMusicNameOrSingerName(@PathVariable String targetText, @PageableDefault Pageable pageable) throws MalformedURLException {
        return ResponseEntity.ok(musicService.getMusicByMusicNameOrSingerName(targetText, pageable));
    }

    @GetMapping("/weather")
    public ResponseEntity<List<ResponseMusicDTO>> getMusicByWeather(HttpServletRequest request, @PageableDefault Pageable pageable) throws IOException, GeoIp2Exception {
       CityResponse cityResponse = geoService.findCity(geoService.getIpAddress());
       Location location = cityResponse.getLocation();
       OpenWeather weather = weatherAPI.getWeather(location.getLatitude().toString(), location.getLongitude().toString());
       String weatherName = weather.getWeather().get(0).getMain();
       return ResponseEntity.ok(musicService.getAllMusicByWeather(WebConfig.getMemberIdByRequest(request), weatherName, pageable));
    }
    @GetMapping("/play/{musicId}")
    public ResponseEntity<ResponseLyricsDTO> getLyricsByMusicId(@PathVariable Integer musicId) throws IOException {
        return ResponseEntity.ok(musicService.getLyricsByMusicId(musicId));
    }
    @PostMapping("/upload")
    public ResponseEntity<ResponseMusicDTO> uploadMusic(HttpServletRequest request, @ModelAttribute UploadMusicDTO uploadMusicDTO) throws UnsupportedAudioFileException, IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(musicService.addMusic(uploadMusicDTO, WebConfig.getMemberIdByRequest(request)));
    }
    @PutMapping("/modify")
    public ResponseEntity<ResponseMusicDTO> modifyMusic(@ModelAttribute ModifyMusicDTO modifyMusicDTO) throws UnsupportedAudioFileException, IOException {
        return ResponseEntity.ok(musicService.modifyMusic(modifyMusicDTO));
    }
    @DeleteMapping("/delete/{musicId}")
    public ResponseEntity<Void> deleteMusic(HttpServletRequest request, @PathVariable Integer musicId) throws IOException {
        musicService.deleteMusic(musicId, WebConfig.getMemberIdByRequest(request));
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/dislike/{musicId}")
    public ResponseEntity<?> dislike(HttpServletRequest request, @PathVariable Integer musicId) {
        likeService.dislike(musicId, WebConfig.getMemberIdByRequest(request));
        return ResponseEntity.ok("음악 싫어요 완료.");
    }

    @PostMapping("/like/{musicId}")
    public ResponseEntity<?> like(HttpServletRequest request, @PathVariable Integer musicId) {
        likeService.like(musicId, WebConfig.getMemberIdByRequest(request));
        return ResponseEntity.ok("음악 좋아요 완료.");
    }

    @DeleteMapping("/like/{musicId}")
    public ResponseEntity<Void> deleteLike(HttpServletRequest request, @PathVariable Integer musicId) {
        likeService.deleteLikeAndDislike(musicId, WebConfig.getMemberIdByRequest(request));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/dislike/{musicId}")
    public ResponseEntity<Void> deleteDislike(HttpServletRequest request, @PathVariable Integer musicId) {
        likeService.deleteLikeAndDislike(musicId, WebConfig.getMemberIdByRequest(request));
        return ResponseEntity.noContent().build();
    }
}
