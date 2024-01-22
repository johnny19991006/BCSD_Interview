package BCSD.MusicStream.controller;

import BCSD.MusicStream.dto.AddPlaylistDTO;
import BCSD.MusicStream.dto.ModefiedPlaylistDTO;
import BCSD.MusicStream.service.PlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlistMusics")
@AllArgsConstructor
public class PlaylistMusicController {

    private final PlaylistService playlistService;
    @PostMapping
    public void createPlaylist(@RequestBody AddPlaylistDTO playlistDTO) {
        playlistService.addPlaylist(playlistDTO);
    }
    @PutMapping
    public void modefiedPlaylistName(@RequestBody ModefiedPlaylistDTO modefiedPlaylistDTO) {
        playlistService.modefiedPlaylistName(modefiedPlaylistDTO);
    }
    @DeleteMapping
    public void deletePlaylist(@PathVariable Integer playlistId) {
        playlistService.removePlaylist(playlistId);
    }
}
