package BCSD.MusicStream.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(of = "user_id")
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    private String user_name;
    @Column(name = "user_email")
    private String user_email;
    @Column(name = "user_pw")
    private String user_pw;
    private LocalDate birth_date;
    private Boolean authority_type;

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(authority_type.toString()));
        return list;
    }
    @Override
    public String getPassword() {
        return null;
    }
    @Override
    public String getUsername() {
        return null;
    }
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
    @Override
    public boolean isEnabled() {
        return false;
    }
}
