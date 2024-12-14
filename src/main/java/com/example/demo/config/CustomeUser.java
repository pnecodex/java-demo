package com.example.demo.config;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class CustomeUser implements UserDetails {

  private String userName;
  private String password;

  private Boolean isEnabled;
  private List<GrantedAuthority> authorities;

  public CustomeUser(User user) {
    this.userName = user.getEmail();
    this.password = user.getPassword();
    this.isEnabled = user.isEnabled();
    this.authorities =
      Arrays.stream(user.getRoles().toString().split(","))
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }


  @Override
  public String getPassword() {
    return password;
  }

  //    @Override
//    public Collection<? extends GrantedAuthority>  getAuthorities() {
//        List<Role> roles = user.getRoles();
//        String pass = user.getPassword();
//        System.out.println("getauthorities roles" + pass);
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//        for (Role role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        return authorities;
//    }
  @Override
  public String getUsername() {
    return userName;
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
    return isEnabled;
  }
}
