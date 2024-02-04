package BCSD.MusicStream.controller;

import BCSD.MusicStream.config.WebConfig;
import BCSD.MusicStream.dto.playlist.AddPlaylistDTO;
import BCSD.MusicStream.dto.playlist.ModifyPlaylistDTO;
import BCSD.MusicStream.dto.playlist.ResponsePlaylistDTO;
import BCSD.MusicStream.security.JwtTokenProvider;
import BCSD.MusicStream.service.PlaylistService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
    public ResponseEntity<List<ResponsePlaylistDTO>> getPlaylist(HttpServletRequest request, @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(playlistService.getPlaylistByMemberId(WebConfig.getMemberIdByRequest(request), PageRequest.of(page, 10)));
    }
    @PostMapping
    public ResponseEntity<ResponsePlaylistDTO> createPlaylist(HttpServletRequest request,  @Valid @RequestBody AddPlaylistDTO addPlaylistDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playlistService.addPlaylist(addPlaylistDTO, WebConfig.getMemberIdByRequest(request)));
    }
    @PutMapping
    public ResponseEntity<ResponsePlaylistDTO> modifyPlaylistName(HttpServletRequest request, @Valid @RequestBody ModifyPlaylistDTO modifyPlaylistDTO) {
        return ResponseEntity.ok(playlistService.modifyPlaylistName(modifyPlaylistDTO));
    }
    @DeleteMapping("/{playlistId}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Integer playlistId) {
        playlistService.removePlaylist(playlistId);
        return ResponseEntity.noContent().build();
    }
}
