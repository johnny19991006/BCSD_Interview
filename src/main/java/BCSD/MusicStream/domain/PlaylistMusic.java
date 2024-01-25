package BCSD.MusicStream.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "playlist_music")
public class PlaylistMusic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "music_id", referencedColumnName = "id")
    private Music music;

    @ManyToOne
    @JoinColumn(name = "playlist_id", referencedColumnName = "id")
    private Playlist playlist;
}
