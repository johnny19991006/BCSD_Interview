package BCSD.MusicStream.dto.playlistMusic;

import BCSD.MusicStream.domain.Music;
import lombok.Builder;
import lombok.Getter;

import java.sql.Time;

@Getter
@Builder
public class RequestPlaylistMusicDTO {
    private Integer id;
    private Integer musicId;
    private String musicName;
    private String singerName;
    private Time duration;
    private String imageFilePath;
    private String soundFilePath;
}
