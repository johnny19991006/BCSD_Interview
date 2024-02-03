package BCSD.MusicStream.controller;

import BCSD.MusicStream.config.WebConfig;
import BCSD.MusicStream.dto.playlistMusic.AddPlaylistMusicDTO;
import BCSD.MusicStream.dto.playlistMusic.ResponsePlaylistMusicDTO;
import BCSD.MusicStream.service.PlaylistMusicsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<List<ResponsePlaylistMusicDTO>> getPlaylistMusicsByPlaylistId(HttpServletRequest request, @PathVariable Integer playlistId, @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(playlistMusicsService.findAllMusicByPlaylistId(playlistId, WebConfig.getMemberIdByRequest(request), PageRequest.of(page, 10)));
    }
    @PostMapping
    public ResponseEntity<AddPlaylistMusicDTO> addPlaylistMusic(HttpServletRequest request,@Valid @RequestBody AddPlaylistMusicDTO addPlaylistMusicDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playlistMusicsService.addMusic(addPlaylistMusicDTO, WebConfig.getMemberIdByRequest(request)));
    }
    @DeleteMapping("/{playlistMusicId}")
    public ResponseEntity<Void> deletePlaylistMusicByPlaylistMusicId(HttpServletRequest request,@PathVariable Integer playlistMusicId) {
        playlistMusicsService.deleteMusicByPlaylistMusicId(playlistMusicId, WebConfig.getMemberIdByRequest(request));
        return ResponseEntity.noContent().build();
    }
}
