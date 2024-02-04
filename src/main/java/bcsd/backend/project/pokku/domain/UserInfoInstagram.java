package bcsd.backend.project.pokku.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_info_instagram")
public class UserInfoInstagram {

    @Id
    @Column(name = "user_instagram")
    private String userInstagram;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

    @Builder
    public UserInfoInstagram(String userInstagram, UserInfo userInfo){
        this.userInstagram = userInstagram;
        this.userInfo = userInfo;
    }

}
