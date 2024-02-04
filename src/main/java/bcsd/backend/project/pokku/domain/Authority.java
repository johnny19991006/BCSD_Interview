package bcsd.backend.project.pokku.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authority")
@Getter
@NoArgsConstructor
public class Authority {
    @Id
    @Column(name = "auth_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AuthId;

    @Column(name = "auth_name")
    private String AuthName;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserInfo userInfo;

    public void setUsers(UserInfo user){
        this.userInfo = user;
    }

    @Builder
    public Authority(UserInfo userInfo, String authName){
        this.userInfo = userInfo;
        this.AuthName = authName;
    }

}
