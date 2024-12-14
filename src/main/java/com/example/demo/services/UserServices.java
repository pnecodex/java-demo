package com.example.demo.services;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
//import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class UserServices {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;


  public User saveUser( User user, boolean isEnabled) {

        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setEnabled(isEnabled);
        user.setRoles( Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
  }
}
