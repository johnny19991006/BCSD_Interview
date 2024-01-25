package BCSD.MusicStream.controller;

import BCSD.MusicStream.dto.playlist.AddPlaylistDTO;
import BCSD.MusicStream.dto.playlistMusic.AddPlaylistMusicDTO;
import BCSD.MusicStream.dto.playlistMusic.RequestPlaylistMusicDTO;
import BCSD.MusicStream.service.PlaylistMusicsService;
import BCSD.MusicStream.service.PlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlistMusics")
@AllArgsConstructor
public class PlaylistMusicController {

    private final PlaylistMusicsService playlistMusicsService;

    @GetMapping("/{playlistId}")
    public List<RequestPlaylistMusicDTO> getPlaylistMusicsByPlaylistId(@PathVariable Integer playlistId) {
        return playlistMusicsService.findAllMusicByPlaylistId(playlistId);
    }
    @PostMapping
    public void addPlaylistMusic(@RequestBody AddPlaylistMusicDTO addPlaylistMusicDTO) {
        playlistMusicsService.addMusic(addPlaylistMusicDTO);
    }
    @DeleteMapping("/{playlistMusicId}")
    public void deletePlaylistMusicByPlaylistMusicId(@PathVariable Integer playlistMusicId) {
        playlistMusicsService.removeMusicByPlaylistMusicId(playlistMusicId);
    }
}
