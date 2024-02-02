package HSAnimal.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Builder
@Entity(name = "users")
@NoArgsConstructor
@Getter
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "user_id", unique = true, nullable = false, length = 100)
    private String userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true , nullable = false)
    private String email;

    public User(int id, String userId, String username, String password, String email){
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void changeName (String username){
        this.username = username;
    }

    public void changePassword (String password){
        this.password = password;
    }

    public void changeEmail (String email){
        this.email = email;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }
}

