package BCSD.MusicStream.controller;

import BCSD.MusicStream.dto.playlistMusic.AddPlaylistMusicDTO;
import BCSD.MusicStream.dto.playlistMusic.ResponsePlaylistMusicDTO;
import BCSD.MusicStream.service.PlaylistMusicsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist-musics")
@AllArgsConstructor
public class PlaylistMusicController {

    private final PlaylistMusicsService playlistMusicsService;

    @GetMapping("/{playlistId}")
    public ResponseEntity<List<ResponsePlaylistMusicDTO>> getPlaylistMusicsByPlaylistId(@PathVariable Integer playlistId) {
        return ResponseEntity.ok(playlistMusicsService.findAllMusicByPlaylistId(playlistId));
    }
    @PostMapping
    public ResponseEntity<AddPlaylistMusicDTO> addPlaylistMusic(@RequestBody AddPlaylistMusicDTO addPlaylistMusicDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playlistMusicsService.addMusic(addPlaylistMusicDTO));
    }
    @DeleteMapping("/{playlistMusicId}")
    public ResponseEntity<Void> deletePlaylistMusicByPlaylistMusicId(@PathVariable Integer playlistMusicId) {
        playlistMusicsService.deleteMusicByPlaylistMusicId(playlistMusicId);
        return ResponseEntity.noContent().build();
    }
}
