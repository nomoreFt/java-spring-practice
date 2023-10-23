package com.my.javaspringpractice.service;

import com.my.javaspringpractice.domain.Article;
import com.my.javaspringpractice.domain.type.SearchType;
import com.my.javaspringpractice.dto.ArticleDto;
import com.my.javaspringpractice.dto.ArticleUpdateDto;
import com.my.javaspringpractice.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks private ArticleService sut;
    @Mock private ArticleRepository articleRepository;


    @DisplayName("게시글을 검색하면, 게시글 리스트를 반환한다.")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList(){
        //given
        //when
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE,"search keyword");//제목, 본문, Id, 닉네임, 해시태그
        //then
        assertThat(articles).isNotNull();
    }

    @DisplayName("게시글을 조회하면 게시글을 반환한다.")
    @Test
    void givenArticleId_whenSearchingArticle_thenReturnsArticle(){
        //given
        //when
        ArticleDto dto = sut.searchArticle(1L);
        //then
        assertThat(dto).isNotNull();
    }

    @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다.")
    @Test
    void givenSavingArticleInfo_whenSavingAtricle_thenSaveArticles(){
        //given
        ArticleDto dto = ArticleDto.of( LocalDateTime.now(), "title", "content", "hashtag", "nickname");
        given(articleRepository.save(any(Article.class))).willReturn(null);

        //when
        sut.saveArticle(dto);
        //then
        then(articleRepository).should(times(1)).save(any(Article.class));
    }


    @DisplayName("게시글 ID, 수정정보를 입력하면, 게시글을 수정한다.")
    @Test
    void givenArticleIdAndModifiedInfo_whenUpdatingAtricle_thenUpdateArticles(){
        //given
        ArticleUpdateDto dto = ArticleUpdateDto.of("title", "content", "hashtag");
        given(articleRepository.save(any(Article.class))).willReturn(null);

        //when
        sut.updateArticle(1L, dto);
        //then
        then(articleRepository).should(times(1)).save(any(Article.class));
    }

    @DisplayName("게시글 ID를 입력하면, 게시글을 삭제한다.")
    @Test
    void givenArticleId_whenDeletingAtricle_thenDeleteArticles(){
        //given
        willDoNothing().given(articleRepository).deleteById(anyLong());

        //when
        sut.deleteArticle(1L);
        //then
        then(articleRepository).should(times(1)).deleteById(anyLong());
    }


}