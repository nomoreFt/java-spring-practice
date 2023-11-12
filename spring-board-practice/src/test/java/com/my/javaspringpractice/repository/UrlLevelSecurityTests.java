package com.my.javaspringpractice.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
public class UrlLevelSecurityTests {

    @Autowired
    WebApplicationContext context;

    @Autowired
    FilterChainProxy filterChainProxy;

    private MockMvc mvc;

    @BeforeEach
    void setUp(){
        this.mvc = webAppContextSetup(context).addFilters(filterChainProxy).build();
        SecurityContextHolder.clearContext();
    }

    @DisplayName("인증되지 않은 유저는 API 요청시에 로그인 폼으로 Redirection된다.")
    @Test
    void givenUnAuthenticatedUser_whenRequestingAPI_thenRedirectingLoginForm() throws Exception {
        mvc.perform(get("/api/articles")
                        .accept(MediaTypes.HAL_JSON))
                .andExpect(status().is3xxRedirection());
    }
    @DisplayName("ADMIN ROLE을 가진 유저만 UserAccount API 요청시에 정상적으로 응답을 받는다.")
    @Test
    void giveAdminUser_whenRequestingUserAccountAPI_then200Success() throws Exception {
        //given
        String username = "admin";
        String role = "ADMIN";
        //when
        mvc.perform(get("/api/userAccounts")
                        .with(user(username).roles(role))
                        .accept(MediaTypes.HAL_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON));

    }

    @DisplayName("USER ROLE을 가진 유저가 UserAccount API 요청시에 403 Forbidden 응답을 받는다.")
    @Test
    void giveUserROLE_whenRequestingUserAccountAPI_then403Fail() throws Exception {
        //given
        String username = "user";
        String role = "USER";
        //when
        mvc.perform(get("/api/userAccounts")
                        .with(user(username).roles(role))
                        .accept(MediaTypes.HAL_JSON))
                .andExpect(status().isForbidden());
    }

    @DisplayName("USER ROLE을 가진 유저가 Article API 요청시에 정상적으로 응답을 받는다.")
    @Test
    void givenUserRole_whenRequestingArticleAPI_thenReturns200() throws Exception{
        //given
        String username = "user";
        String role = "USER";

        //when & then
        mvc.perform(get("/api/articles")
                        .with(user(username).roles(role))
                        .accept(MediaTypes.HAL_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON));
    }



}
