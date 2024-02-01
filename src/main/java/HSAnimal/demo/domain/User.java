package HSAnimal.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Builder        // SQL 파라미터에 값을 쉽게 넣어주기 위함
@Entity(name = "users")  // 해당 class에서 사용할 테이블 명
@Getter             // get 메소드를 자동 생성
@Setter             // set 메소드를 자동 생성
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "user_id", unique = true, nullable = false, length = 100)
    private String userId; //유저id

    @Column(name = "username", nullable = false, length = 255)
    private String username; //유저이름

    @Column(name = "password", nullable = false)
    private String password; //비밀번호

    @Column(name = "email", unique = true , nullable = false)
    private String email; //비밀번호

    public User(){}

    public User(int id, String userId, String username, String password, String email){
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }
}

