package bcsd.backend.project.pokku.dto.UserInfoSNS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserInfoSNSResponse {
    private String userId;
    private String userGithub;
    private String userBlog;
    private String userInstagram;
}
