package BCSD.MusicStream.dto;

import lombok.*;

import java.time.LocalTime;
@Data
@AllArgsConstructor
@Builder
public class MusicDTO {
    private Integer musicId;
    private String musicName;
    private String categoryName;
    private String singerName;
    private Integer userId;
    private LocalTime musicTime;
    private String musicFileUrl;
    private String musicIconUrl;
}
