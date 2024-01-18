package BCSD.MusicStream.controller;

import BCSD.MusicStream.domain.Music;
import BCSD.MusicStream.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/music")
public class MusicController {
    @Autowired
    private MusicService musicService;
    @GetMapping("/{musicName}")
    public ResponseEntity<List<Music>> getMusicByMusicName(@PathVariable String musicName) {
        return ResponseEntity.ok(musicService.getMusicByMusicName(musicName));
    }
}
