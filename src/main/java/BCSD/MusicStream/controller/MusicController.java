package BCSD.MusicStream.controller;

import BCSD.MusicStream.api.OpenWeather;
import BCSD.MusicStream.api.WeatherAPI;
import BCSD.MusicStream.config.WebConfig;
import BCSD.MusicStream.dto.music.*;
import BCSD.MusicStream.exception.CustomErrorCodeException;
import BCSD.MusicStream.exception.CustomException;
import BCSD.MusicStream.exception.ErrorCode;
import BCSD.MusicStream.service.GeoService;
import BCSD.MusicStream.service.LikeService;
import BCSD.MusicStream.service.MusicService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Location;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public ResponseEntity<List<ResponseMusicDTO>> getAllMusic(HttpServletRequest request, @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(musicService.getAllMusic(WebConfig.getMemberIdByRequest(request), PageRequest.of(page, 10)));
    }
    @GetMapping("/{targetText}")
    public ResponseEntity<List<ResponseMusicDTO>> getMusicByMusicNameOrSingerName(@PathVariable String targetText, @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(musicService.getMusicByMusicNameOrSingerName(targetText, PageRequest.of(page, 10)));
    }

    @GetMapping("/info/{musicId}")
    public ResponseEntity<MusicInfoDTO> getMusicByMusicNameOrSingerName(@PathVariable Integer musicId, HttpServletRequest request) {
        return ResponseEntity.ok(musicService.getMusicInfo(musicId, WebConfig.getMemberIdByRequest(request)));
    }

    @GetMapping("/weather")
    public ResponseEntity<List<ResponseMusicDTO>> getMusicByWeather(HttpServletRequest request, @RequestParam(defaultValue = "0") int page) {
        try {
            CityResponse cityResponse = geoService.findCity(geoService.getIpAddress());
            Location location = cityResponse.getLocation();
            OpenWeather weather = weatherAPI.getWeather(location.getLatitude().toString(), location.getLongitude().toString());
            String weatherName = weather.getWeather().get(0).getMain();
            return ResponseEntity.ok(musicService.getAllMusicByWeather(WebConfig.getMemberIdByRequest(request), weatherName, PageRequest.of(page, 10)));
        }catch(IOException ioException){
            throw new CustomException(HttpStatus.BAD_GATEWAY, "API-502", ioException.getMessage());
        }catch(GeoIp2Exception geoIp2Exception) {
            throw new CustomException(HttpStatus.BAD_GATEWAY, "API-502", geoIp2Exception.getMessage());
        }
    }
    @GetMapping("/play/{musicId}")
    public ResponseEntity<ResponsePlayMusicDTO> getLyricsAndLikeByMusicId(HttpServletRequest request, @PathVariable Integer musicId) {
        return ResponseEntity.ok(musicService.getLyricsAndLikeByMusicId(musicId, WebConfig.getMemberIdByRequest(request)));
    }
    @PostMapping("/upload")
    public ResponseEntity<ResponseMusicDTO> uploadMusic(HttpServletRequest request, @Valid @ModelAttribute UploadMusicDTO uploadMusicDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(musicService.addMusic(uploadMusicDTO, WebConfig.getMemberIdByRequest(request)));
    }
    @PutMapping("/modify")
    public ResponseEntity<ResponseMusicDTO> modifyMusic(@Valid @ModelAttribute ModifyMusicDTO modifyMusicDTO, HttpServletRequest request) {
        return ResponseEntity.ok(musicService.modifyMusic(modifyMusicDTO, WebConfig.getMemberIdByRequest(request)));
    }
    @DeleteMapping("/delete/{musicId}")
    public ResponseEntity<Void> deleteMusic(HttpServletRequest request, @PathVariable Integer musicId) {
        System.out.println("dsa");
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
