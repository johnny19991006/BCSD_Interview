package BCSD.MusicStream.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playlist_id;
    private String playlist_name;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "User")
    private Users user;
    private String playlist_musics;
    @Builder
    Playlist(Integer playlist_id, String playlist_name, Integer user_id, String playlist_musics) {
        this.playlist_id = playlist_id;
        this.playlist_name = playlist_name;
        this.playlist_musics = playlist_musics;
    }
}
