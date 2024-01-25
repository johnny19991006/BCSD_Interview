package BCSD.MusicStream.controller;

import BCSD.MusicStream.dto.playlist.AddPlaylistDTO;
import BCSD.MusicStream.dto.playlist.ModifyPlaylistDTO;
import BCSD.MusicStream.dto.playlist.RequestPlaylistDTO;
import BCSD.MusicStream.security.JwtTokenProvider;
import BCSD.MusicStream.service.PlaylistService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist")
@AllArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;
    @GetMapping
    public List<RequestPlaylistDTO> getPlaylist(HttpServletRequest request) {
        Claims cLaims = JwtTokenProvider.parseClaims(JwtTokenProvider.extractJwtFromRequest(request));
        Integer memberId = (Integer) cLaims.get("memberId");
        return playlistService.getPlaylistByMemberId(memberId);
    }
    @PostMapping
    public void createPlaylist(HttpServletRequest request,  @RequestBody AddPlaylistDTO addPlaylistDTO) {
        System.out.println("ds");
        Claims cLaims = JwtTokenProvider.parseClaims(JwtTokenProvider.extractJwtFromRequest(request));
        Integer memberId = (Integer) cLaims.get("memberId");
        playlistService.addPlaylist(addPlaylistDTO, memberId);
    }
    @PutMapping
    public void modefiedPlaylistName(HttpServletRequest request, @RequestBody ModifyPlaylistDTO modifyPlaylistDTO) {
        playlistService.modifyPlaylistName(modifyPlaylistDTO);
    }
    @DeleteMapping("/{playlistId}")
    public void deletePlaylist(@PathVariable Integer playlistId) {
        playlistService.removePlaylist(playlistId);
    }
}
