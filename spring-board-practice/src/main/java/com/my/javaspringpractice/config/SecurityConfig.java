package com.my.javaspringpractice.config;

import com.my.javaspringpractice.domain.UserAccount;
import com.my.javaspringpractice.domain.UserId;
import com.my.javaspringpractice.dto.UserAccountDto;
import com.my.javaspringpractice.dto.security.BoardPrincipal;
import com.my.javaspringpractice.repository.UserAccountRepository;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers(HttpMethod.GET).permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/articles").permitAll()
                        .requestMatchers("/articles/search-hashtag").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formlogin -> formlogin.permitAll())
                .logout(logout -> logout.logoutSuccessUrl("/"))
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserAccountRepository userAccountRepository) {
        return username -> userAccountRepository
                .findById(UserId.of(username))
                .map(UserAccountDto::from)
                .map(BoardPrincipal::from)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다 - username: " + username));

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
