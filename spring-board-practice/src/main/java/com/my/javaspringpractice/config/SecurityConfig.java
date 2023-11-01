package com.my.javaspringpractice.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

}
