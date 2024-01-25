package BCSD.MusicStream.controller;

import BCSD.MusicStream.dto.Lyrics.RequestLyricsDTO;
import BCSD.MusicStream.dto.music.RequestMusicDTO;
import BCSD.MusicStream.dto.music.UploadMusicDTO;
import BCSD.MusicStream.security.JwtTokenProvider;
import BCSD.MusicStream.service.MusicService;
import BCSD.MusicStream.service.PlaylistService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
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
//    private final PlaylistService playlistService;
    @GetMapping("/{targetText}")
    public ResponseEntity<List<RequestMusicDTO>> getMusicByMusicName(@PathVariable String targetText) throws MalformedURLException {
        return ResponseEntity.ok(musicService.getMusicByMusicNameOrSingerName(targetText));
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
//    @PutMapping
//    public ResponseEntity<?> modefiedMusic(@ModelAttribute ModefiedMusicDTO modefiedMusicDTO) {
//        try {
//            if(!modefiedMusicDTO.getMusicFile().isEmpty()) {
//                musicService.deleteMusicMP3(modefiedMusicDTO.getMusicId());
//                musicService.uploadMusicMP3(modefiedMusicDTO.getMusicId(), modefiedMusicDTO.getMusicFile());
//            }
//            if(!modefiedMusicDTO.getMusicIcon().isEmpty()) {
//                musicService.deleteMusicIcon(modefiedMusicDTO.getMusicId());
//                musicService.uploadMusicIcon(modefiedMusicDTO.getMusicId(), modefiedMusicDTO.getMusicIcon());
//            }
//            musicService.modefiedMusic(modefiedMusicDTO);
//            return ResponseEntity.ok("Music modefied and metadata saved successfully");
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
//        }
//    }
    @DeleteMapping("/{musicId}")
    public ResponseEntity<?> deleteMusic(HttpServletRequest request, @PathVariable Integer musicId) {
        Claims cLaims = JwtTokenProvider.parseClaims(JwtTokenProvider.extractJwtFromRequest(request));
        Integer memberId = (Integer) cLaims.get("memberId");
        musicService.deleteMusic(musicId, memberId);
        return ResponseEntity.ok("delete Ok!!");
    }
}
