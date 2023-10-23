package com.my.javaspringpractice.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled("Data REST 통합 테스트는 불필요하므로 제외한다.")
@DisplayName("Data REST - API Test")
@Transactional//spring Transactional (data rest가 hibernate쿼리를 이용해서 사용하기 때문에 DB에 영향이 간다.)
@AutoConfigureMockMvc //Repository에 선언되었기 때문에 @WebMvcTest를 사용할 수 없다.
@SpringBootTest
public class DataRestTest {

    private final MockMvc mockMvc;


    public DataRestTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @DisplayName("[api] 게시글 리스트 조회")
    @Test
    void givenNothing_whenRequestingArticles_thenReturnsArticlesJsonResponse() throws Exception {
        //Given

        //When & Then
        mockMvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"));


    }

    @DisplayName("[api] 게시글 조회")
    @Test
    void givenNothing_whenRequestingArticle_thenReturnsArticleJsonResponse() throws Exception {
        //Given

        //When & Then
        mockMvc.perform(get("/api/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"));


    }

    @DisplayName("[api] 게시글 -> 댓글 리스트 조회")
    @Test
    void givenNothing_whenRequestingArticleArticleComments_thenReturnsArticleArticleCommentsJsonResponse() throws Exception {
        //Given

        //When & Then
        mockMvc.perform(get("/api/articles/1/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"));
    }

    @DisplayName("[api] 댓글 리스트 조회")
    @Test
    void givenNothing_whenRequestingArticleComments_thenReturnsArticleCommentsJsonResponse() throws Exception {
        //Given

        //When & Then
        mockMvc.perform(get("/api/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"));
    }

    @DisplayName("[api] 댓글 단건 조회")
    @Test
    void givenNothing_whenRequestingArticleComment_thenReturnsArticleCommentJsonResponse() throws Exception {
        //Given

        //When & Then
        mockMvc.perform(get("/api/articleComments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"));
    }


    @DisplayName("[api] 회원 관련 API 는 일체 제공하지 않는다.")
    @Test
    void givenNothing_whenRequestingUserAccounts_thenThrowsException() throws Exception {
        // Given

        // When & Then
        mockMvc.perform(get("/api/userAccounts")).andExpect(status().isForbidden());
        mockMvc.perform(post("/api/userAccounts")).andExpect(status().isForbidden());
        mockMvc.perform(put("/api/userAccounts")).andExpect(status().isForbidden());
        mockMvc.perform(patch("/api/userAccounts")).andExpect(status().isForbidden());
        mockMvc.perform(delete("/api/userAccounts")).andExpect(status().isForbidden());
        mockMvc.perform(head("/api/userAccounts")).andExpect(status().isForbidden());
    }
}


