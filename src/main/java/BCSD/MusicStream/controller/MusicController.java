package BCSD.MusicStream.controller;

import BCSD.MusicStream.dto.AddMusicDTO;
import BCSD.MusicStream.dto.ModefiedMusicDTO;
import BCSD.MusicStream.dto.MusicDTO;
import BCSD.MusicStream.service.MusicService;
import BCSD.MusicStream.service.PlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/music")
@AllArgsConstructor
public class MusicController {
    private final MusicService musicService;
    private final PlaylistService playlistService;
    @GetMapping("/{musicName}")
    public ResponseEntity<List<MusicDTO>> getMusicByMusicName(@PathVariable String musicName) throws MalformedURLException {
        return ResponseEntity.ok(musicService.getMusicByMusicNameOrSingerName(musicName));
    }
//    @GetMapping("/{musicName}/{musicId}")
//    public ResponseEntity<List<MusicDTO>> getMusicByM2usicName(@PathVariable String musicName) throws MalformedURLException {
//        return ResponseEntity.ok(musicService.getMusicByMusicNameOrSingerName(musicName));
//    }
    @PostMapping
    public ResponseEntity<?> uploadMusic(@ModelAttribute AddMusicDTO addMusicDTO) {
        try {
            if (addMusicDTO.getMusicFile().isEmpty() || addMusicDTO.getMusicIcon().isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
            musicService.addMusic(addMusicDTO);
            return ResponseEntity.ok("Music uploaded and metadata saved successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity<?> modefiedMusic(@ModelAttribute ModefiedMusicDTO modefiedMusicDTO) {
        try {
            if(!modefiedMusicDTO.getMusicFile().isEmpty()) {
                musicService.deleteMusicMP3(modefiedMusicDTO.getMusicId());
                musicService.uploadMusicMP3(modefiedMusicDTO.getMusicId(), modefiedMusicDTO.getMusicFile());
            }
            if(!modefiedMusicDTO.getMusicIcon().isEmpty()) {
                musicService.deleteMusicIcon(modefiedMusicDTO.getMusicId());
                musicService.uploadMusicIcon(modefiedMusicDTO.getMusicId(), modefiedMusicDTO.getMusicIcon());
            }
            musicService.modefiedMusic(modefiedMusicDTO);
            return ResponseEntity.ok("Music modefied and metadata saved successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    @DeleteMapping("/{musicId}")
    public ResponseEntity<?> deleteMusic(@PathVariable Integer musicId) {
        musicService.deleteMusicIcon(musicId);
        musicService.deleteMusicMP3(musicId);
        musicService.deleteMusic(musicId);
        return ResponseEntity.ok("delete Ok!!");
    }
}
