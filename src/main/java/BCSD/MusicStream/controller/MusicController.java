package BCSD.MusicStream.controller;

import BCSD.MusicStream.domain.Music;
import BCSD.MusicStream.dto.AddMusicDTO;
import BCSD.MusicStream.dto.MusicDTO;
import BCSD.MusicStream.service.MusicService;
import BCSD.MusicStream.service.PlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/music")
@AllArgsConstructor
public class MusicController {
    private final MusicService musicService;
    private final PlaylistService playlistService;

    private static final String MUSIC_FILE_DIR = "src/main/resources/static/musicFile/";
    private static final String MUSIC_ICON_DIR = "src/main/resources/static/musicIcon/";
    @GetMapping("/{musicName}")
    public ResponseEntity<List<MusicDTO>> getMusicByMusicName(@PathVariable String musicName) throws MalformedURLException {
        return ResponseEntity.ok(musicService.getMusicByMusicNameOrSingerName(musicName));
    }
    @PostMapping("/add-music")
    public void addMusic(@RequestBody AddMusicDTO addmusicDTO) {
        musicService.addMusic(addmusicDTO);
    }
    @PostMapping("/upload-mp3")
    public String uploadMp3(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) return "File is empty";
        try {
            Path path = Paths.get(MUSIC_FILE_DIR + file.getOriginalFilename());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return "File uploaded successfully";
        } catch (IOException e) {
            return "File upload error: " + e.getMessage();
        }
    }
}
