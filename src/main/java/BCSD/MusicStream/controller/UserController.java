package BCSD.MusicStream.controller;

import BCSD.MusicStream.domain.Music;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/login")
    public ResponseEntity<List<Music>> getAccesToken(@PathVariable String email, String password) {
        return null;
    }

}
