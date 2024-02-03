package BCSD.MusicStream.dto.music;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Time;

@Getter
@Builder
public class UploadMusicDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Category ID cannot be null")
    private Integer categoryId;

    @NotNull(message = "Weather ID cannot be null")
    private Integer weatherId;

    @NotBlank(message = "Singer name cannot be blank")
    private String singerName;

    @NotBlank(message = "Lyrics cannot be blank")
    private String lyrics;

    @Pattern(regexp = "^([01]?\\d|2[0-3]):[0-5]\\d:[0-5]\\d$", message = "Duration must be in the format HH:mm:ss")
    private Time duration;

    @NotNull(message = "Image file cannot be null")
    private MultipartFile imageFile;

    @NotNull(message = "Sound file cannot be null")
    private MultipartFile soundFile;
}
