package com.jayant.Social_Media_Backend.Security;

import com.jayant.Social_Media_Backend.entities.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Data
public class JWTUserDetails implements UserDetails {

    public Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private JWTUserDetails(Long id , String name , String pass ,Collection<? extends GrantedAuthority> authorities){
        this.id = id;
        this.username = name;
        this.password = pass;
        this.authorities = authorities;
    }

    public static JWTUserDetails create(User user){
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("user"));
        return new JWTUserDetails(user.getId() , user.getUsername() , user.getPassword(), authorityList);
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
