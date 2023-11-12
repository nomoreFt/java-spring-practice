package com.my.javaspringpractice.repository;

import com.my.javaspringpractice.config.SecurityUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MethodLevelSecurityTests {
    @Autowired ArticleRepository articleRepository;

    @BeforeEach
    void setUp(){
        SecurityContextHolder.clearContext();
    }

    @DisplayName("인증되지 않은 유저는 Method 요청시에 에러가 발생한다.")
    @Test
    void givenUnAuthenticatedUser_whenRequestingRepositoryMethod_thenFail(){
        Assertions.assertThatCode(() -> articleRepository.findAll())
                .isInstanceOf(AuthenticationCredentialsNotFoundException.class);
    }
    @DisplayName("User ROLE만 가진 유저는 ADMIN ROLE Method 요청시에 FAIL.")
    @Test
    void givenUserRole_whenRequestingRepositoryMethod_thenFail() {

        SecurityUtils.runAs("system", "system", "ROLE_USER");

        //에러없음
        assertThatCode(() -> articleRepository.findAll())
                .doesNotThrowAnyException();

        assertThatCode(() -> articleRepository.count())
                .isInstanceOf(AccessDeniedException.class);

    }

    @DisplayName("User,Admin ROLE을 가진 유저는 ADMIN ROLE Method 요청시에 정상적으로 응답을 받는다.")
    @Test
    void giveAdminUser_whenRequestingRepositoryMethod_then200Success() {

        SecurityUtils.runAs("system", "system", "ROLE_USER","ROLE_ADMIN");

        //에러없음
        assertThatCode(() -> articleRepository.findAll())
                .doesNotThrowAnyException();

        assertThatCode(() -> articleRepository.count())
                .doesNotThrowAnyException();

    }
}
