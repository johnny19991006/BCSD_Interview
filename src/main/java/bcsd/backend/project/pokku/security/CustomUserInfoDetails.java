package bcsd.backend.project.pokku.security;

import bcsd.backend.project.pokku.domain.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserInfoDetails implements UserDetails {
    private final UserInfo user;

    public CustomUserInfoDetails(UserInfo user){
        this.user = user;
    }

    public final UserInfo getUsers(){
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return user.getAuthorities().stream().map(o -> new SimpleGrantedAuthority(o.getAuthName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword(){
        return user.getUserPassword();
    }

    @Override
    public String getUsername(){
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
