package BCSD.MusicStream.controller;

import BCSD.MusicStream.config.WebConfig;
import BCSD.MusicStream.dto.playlist.AddPlaylistDTO;
import BCSD.MusicStream.dto.playlist.ModifyPlaylistDTO;
import BCSD.MusicStream.dto.playlist.ResponsePlaylistDTO;
import BCSD.MusicStream.security.JwtTokenProvider;
import BCSD.MusicStream.service.PlaylistService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist")
@AllArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;
    @GetMapping
    public ResponseEntity<List<ResponsePlaylistDTO>> getPlaylist(HttpServletRequest request) {
        return ResponseEntity.ok(playlistService.getPlaylistByMemberId(WebConfig.getMemberIdByRequest(request)));
    }
    @PostMapping
    public ResponseEntity<ResponsePlaylistDTO> createPlaylist(HttpServletRequest request,  @RequestBody AddPlaylistDTO addPlaylistDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playlistService.addPlaylist(addPlaylistDTO, WebConfig.getMemberIdByRequest(request)));
    }
    @PutMapping
    public ResponseEntity<ResponsePlaylistDTO> modefiedPlaylistName(HttpServletRequest request, @RequestBody ModifyPlaylistDTO modifyPlaylistDTO) {
        return ResponseEntity.ok(playlistService.modifyPlaylistName(modifyPlaylistDTO));
    }
    @DeleteMapping("/{playlistId}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Integer playlistId) {
        playlistService.removePlaylist(playlistId);
        return ResponseEntity.noContent().build();
    }
}
