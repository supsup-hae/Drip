package com.univ.drip.security;

import com.univ.drip.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final UserDetailsServiceImpl userDetailsService;
  private final DripAuthenticationSuccessHandler dripAuthenticationSuccessHandler;

  @Autowired
  public SecurityConfig(UserDetailsServiceImpl userDetailsService, DripAuthenticationSuccessHandler dripAuthenticationSuccessHandler) {
    this.userDetailsService = userDetailsService;
    this.dripAuthenticationSuccessHandler = dripAuthenticationSuccessHandler;
  }


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorizeRequests ->
            authorizeRequests
                .requestMatchers("/api/page/**", "/", "/css/**", "/js/**", "/fonts/**", "/images/**", "/scss/**", "https**",
                    "/api/member/register")
                .permitAll() // 로그인 페이지는 누구나 접근 가능
                .requestMatchers("/api/page/profile").authenticated()
                .anyRequest().authenticated()    // 나머지 요청은 인증 필요
        )
        .formLogin(formLogin ->
            formLogin
                .loginPage("/api/page/login")           // 커스텀 로그인 페이지
                .usernameParameter("id")
                .passwordParameter("password")
                .successHandler(dripAuthenticationSuccessHandler) // 로그인 성공 시 이동할 페이지
                .permitAll()
        )
        .logout(logout ->
            logout
                .logoutUrl("/api/page/logout")
                .logoutSuccessUrl("/api/page/index")
                .permitAll()
        )
        .exceptionHandling(exceptionHandling ->
            exceptionHandling
                .accessDeniedPage("/access-denied")
        );

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }
}