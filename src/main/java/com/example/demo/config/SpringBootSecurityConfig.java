package com.example.demo.config;

import com.example.demo.services.CustomSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringBootSecurityConfig {


  @Autowired
  CustomSuccessHandler customSuccessHandler;
  @Autowired
  CustomeUserServices customeUserServices;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(customeUserServices);
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    http.
//      csrf(c -> c.disable())
//      .authorizeHttpRequests(
//        (authorize) -> authorize
//          .requestMatchers("/register", "/login", "/saveUser").permitAll()
//          .requestMatchers("product").hasAuthority("ADMIN")
//          .anyRequest().authenticated())
////      .authenticationProvider(daoAuthenticationProvider())
//      .formLogin(
//        form -> form
//          .loginPage("/login")
//          .loginProcessingUrl("/login")
////          .defaultSuccessUrl("/product")
//          .successHandler(customSuccessHandler).permitAll()
//          .permitAll()
//      ).logout(
//        logout -> logout
//          .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//          .permitAll()
//
//      );
//    return http.build();
    return http.csrf().disable()
      .authorizeHttpRequests()
      .requestMatchers(
        "/",
        "/login",
        "/error",
        "/register",
        "/saveUser")
      .permitAll()
      .anyRequest()
      .authenticated()
      .and()
      .formLogin()
      .loginPage("/login")
      .usernameParameter("email")
      .defaultSuccessUrl("/product")
      .permitAll().and()
      .logout()
      .invalidateHttpSession(true)
      .clearAuthentication(true)
      .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
      .logoutSuccessUrl("/")
      .and()
      .build();

//        http.csrf().disable();
//        http.authorizeHttpRequests()
//                .requestMatchers("/signin","/register","/saveUser").permitAll()
//                .requestMatchers("/product/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/signin").loginProcessingUrl("/userLogin").defaultSuccessUrl("/index.html",true).permitAll();
//        return http.build();
  }


//  @Autowired
//  public void SecurityConfig (AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//    authenticationManagerBuilder.userDetailsService(customeUserServices).passwordEncoder(passwordEncoder());
//  }


}
