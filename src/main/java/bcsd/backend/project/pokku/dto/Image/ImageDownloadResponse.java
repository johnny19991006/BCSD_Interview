package bcsd.backend.project.pokku.dto.Image;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ImageDownloadResponse {
    private List<String> base64Images;

    public ImageDownloadResponse(List<String> base64Images) {
        this.base64Images = base64Images;
    }

    public List<String> getBase64Images() {
        return base64Images;
    }

    public void setBase64Images(List<String> base64Images) {
        this.base64Images = base64Images;
    }
}
