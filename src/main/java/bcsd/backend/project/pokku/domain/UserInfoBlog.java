package bcsd.backend.project.pokku.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_info_blog")
public class UserInfoBlog {

    @Id
    @Column(name = "user_blog")
    private String userBlog;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

    @Builder
    public UserInfoBlog(String userBlog, UserInfo userInfo){
        this.userBlog = userBlog;
        this.userInfo = userInfo;
    }

}
