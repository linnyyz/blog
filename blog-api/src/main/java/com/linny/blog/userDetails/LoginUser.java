package com.linny.blog.userDetails;

import com.alibaba.fastjson.annotation.JSONField;
import com.linny.blog.pojo.dao.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private User user;


    private List<String> permissions;

    @JSONField(serialize = false)
    public List<SimpleGrantedAuthority> permissionList;
    public LoginUser(User user){
        this.user = user;
    }

    public LoginUser(User user, List permissions){
        this.user = user;
        this.permissions = permissions;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        permissionList = new ArrayList<>();
        for (String permission : permissions) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(permission);
            permissionList.add(simpleGrantedAuthority);
        }
        return permissionList;
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
