package HSAnimal.demo.configuration;

import HSAnimal.demo.enums.UserRole;
import HSAnimal.demo.domain.User;
import HSAnimal.demo.repository.AdminRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private final User user;
    private final AdminRepository adminRepository;

    public CustomUserDetails (User user, AdminRepository adminRepository){
        this.user = user;
        this.adminRepository = adminRepository;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(UserRole.USER.getRole()));
        if (adminRepository.findByUserId(user.getUserId()).isPresent()){
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getRole()));
        }
        return authorities;
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    @Override
    public String getUsername() {
        return user.getUsername();
    }
    @Override
    public boolean isAccountNonExpired() {
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
