package HSAnimal.demo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "refresh_token")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private int id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    public RefreshToken(String userId, String refreshToken){
        this.userId = userId;
        this.refreshToken = refreshToken;
    }
}
