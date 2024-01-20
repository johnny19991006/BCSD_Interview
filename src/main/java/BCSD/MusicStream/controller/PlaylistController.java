package BCSD.MusicStream.controller;

import BCSD.MusicStream.dto.AddPlaylistDTO;
import BCSD.MusicStream.dto.ModefiedPlaylistDTO;
import BCSD.MusicStream.service.PlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlist")
@AllArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;
    @PostMapping
    public void createPlaylist(@RequestBody AddPlaylistDTO playlistDTO) {
        playlistService.addPlaylist(playlistDTO);
    }
    @PutMapping
    public void modefiedPlaylistName(@RequestBody ModefiedPlaylistDTO modefiedPlaylistDTO) {
        playlistService.modefiedPlaylistName(modefiedPlaylistDTO);
    }
    @DeleteMapping("/{playlistId}")
    public void deletePlaylist(@PathVariable String playlistId) {
        System.out.println(playlistId);
        playlistService.removePlaylist(Integer.parseInt(playlistId));
    }
}
