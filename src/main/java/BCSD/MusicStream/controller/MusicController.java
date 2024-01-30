package BCSD.MusicStream.controller;

import BCSD.MusicStream.api.GeoReader;
import BCSD.MusicStream.api.OpenWeather;
import BCSD.MusicStream.api.WeatherAPI;
import BCSD.MusicStream.dto.lyrics.RequestLyricsDTO;
import BCSD.MusicStream.dto.music.ModifyMusicDTO;
import BCSD.MusicStream.dto.music.RequestMusicDTO;
import BCSD.MusicStream.dto.music.UploadMusicDTO;
import BCSD.MusicStream.security.JwtTokenProvider;
import BCSD.MusicStream.service.GeoService;
import BCSD.MusicStream.service.MusicService;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Location;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final GeoService geoService;
    private final WeatherAPI weatherAPI;
    @GetMapping
    public ResponseEntity<List<RequestMusicDTO>> getAllMusic(HttpServletRequest request, @PageableDefault Pageable pageable) {
        Claims cLaims = JwtTokenProvider.parseClaims(JwtTokenProvider.extractJwtFromRequest(request));
        Integer memberId = (Integer) cLaims.get("memberId");
        return ResponseEntity.ok(musicService.getAllMusic(memberId.longValue(), pageable));
    }
    @GetMapping("/{targetText}")
    public ResponseEntity<List<RequestMusicDTO>> getMusicByMusicName(@PathVariable String targetText) throws MalformedURLException {
        return ResponseEntity.ok(musicService.getMusicByMusicNameOrSingerName(targetText));
    }

    @GetMapping("/music-weather")
    public ResponseEntity<List<RequestMusicDTO>> getMusicByWeather(HttpServletRequest request, @PageableDefault Pageable pageable) throws IOException {
       try {
           CityResponse cityResponse = geoService.findCity(geoService.getIpAddress());
           Location location = cityResponse.getLocation();
           OpenWeather weather = weatherAPI.getWeather(location.getLatitude().toString(), location.getLongitude().toString());
           String weatherName = weather.getWeather().get(0).getMain();
           Claims cLaims = JwtTokenProvider.parseClaims(JwtTokenProvider.extractJwtFromRequest(request));
           Integer memberId = (Integer) cLaims.get("memberId");
           return ResponseEntity.ok(musicService.getAllMusicByWeather(memberId.longValue(), weatherName, pageable));
       }catch(Exception e1) {
           return ResponseEntity.ok(null);
       }
    }
    @GetMapping("/playMusic/{musicId}")
    public ResponseEntity<RequestLyricsDTO> getLyricsByMusicId(@PathVariable Integer musicId) throws IOException {
        return ResponseEntity.ok(musicService.getLyricsByMusicId(musicId));
    }
    @PostMapping
    public ResponseEntity<?> uploadMusic(HttpServletRequest request, @ModelAttribute UploadMusicDTO uploadMusicDTO) {
        try {
            if (uploadMusicDTO.getSoundFile().isEmpty() || uploadMusicDTO.getImageFile().isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
            Claims cLaims = JwtTokenProvider.parseClaims(JwtTokenProvider.extractJwtFromRequest(request));
            Integer memberId = (Integer) cLaims.get("memberId");
            musicService.addMusic(uploadMusicDTO, memberId);
            return ResponseEntity.ok("Music uploaded and metadata saved successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping
    public ResponseEntity modefiedMusic(@ModelAttribute ModifyMusicDTO modifyMusicDTO) {
        try {
            return ResponseEntity.ok(musicService.modifyMusic(modifyMusicDTO));
        } catch (UnsupportedAudioFileException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    @DeleteMapping("/{musicId}")
    public ResponseEntity<?> deleteMusic(HttpServletRequest request, @PathVariable Integer musicId) {
        Claims cLaims = JwtTokenProvider.parseClaims(JwtTokenProvider.extractJwtFromRequest(request));
        Integer memberId = (Integer) cLaims.get("memberId");
        musicService.deleteMusic(musicId, memberId);
        return ResponseEntity.ok("delete Ok!!");
    }
}
