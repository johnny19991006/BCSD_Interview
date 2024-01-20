package BCSD.MusicStream.domain;

import BCSD.MusicStream.domain.Music;
import BCSD.MusicStream.domain.Playlist;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class PlaylistMusics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playlistMusicId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_id")
    private Music music;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;
}
