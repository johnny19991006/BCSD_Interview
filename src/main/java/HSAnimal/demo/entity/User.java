package HSAnimal.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

@Builder        // SQL 파라미터에 값을 쉽게 넣어주기 위함
@Entity(name = "users")  // 해당 class에서 사용할 테이블 명
@AllArgsConstructor // 매개변수에 대한 생성자들을 자동 생성
@NoArgsConstructor  // 기본생성자를 자동 생성
@Getter             // get 메소드를 자동 생성
@Setter             // set 메소드를 자동 생성
@ToString           // toString 메소드 자동 생성
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "user_id", unique = true, nullable = false, length = 100)
    private String userId; //유저id

    @Column(name = "username", nullable = false, length = 255)
    private String username; //유저이름

    @Column(name = "password", nullable = false)
    private String password; //비밀번호

    @Column(name = "email", unique = true , nullable = false)
    private String email; //비밀번호

    // UserDetails 인터페이스의 메서드 구현
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 사용자의 권한 정보를 반환하는 로직을 구현해야 합니다.
        // 이 예제에서는 null을 반환하였으니 실제 권한 로직을 구현해야 합니다.
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정이 만료되지 않음
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 계정이 잠겨있지 않음
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 자격 증명(패스워드)이 만료되지 않음
    }

    @Override
    public boolean isEnabled() {
        return true; // 계정이 활성화되어 있음
    }
}

