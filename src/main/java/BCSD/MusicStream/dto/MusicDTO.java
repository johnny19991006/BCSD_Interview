package BCSD.MusicStream.dto;

import BCSD.MusicStream.domain.Users;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.core.io.Resource;

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
