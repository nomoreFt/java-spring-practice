package com.my.javaspringpractice.service;

import com.my.javaspringpractice.domain.Article;
import com.my.javaspringpractice.domain.ArticleComment;
import com.my.javaspringpractice.dto.ArticleCommentDto;
import com.my.javaspringpractice.repository.ArticleCommentRepository;
import com.my.javaspringpractice.repository.ArticleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks private ArticleCommentService sut;
    @Mock private ArticleCommentRepository articleCommentRepository;
    @Mock private ArticleRepository articleRepository;


    @DisplayName("게시글 ID로 조회하면, 해당하는 댓글 리스트를 반환한다.")
    @Test
    void givenArticleId_whenSearchingArticleComments_thenReturnsComments() {
        //given
        Long articleId = 1L;
        given(articleRepository.findById(articleId)).willReturn(Optional.of(
                Article.of("title", "content", "hashtag")));
        //when
        List<ArticleCommentDto> result = sut.searchArticleComment(1L);
        //then
        Assertions.assertThat(sut).isNotNull();
        then(articleRepository).should(times(1)).findById(articleId);
    }


    @DisplayName("댓글 정보를 입력하면, 댓글을 저장한다.")
    @Test
    void givenArticleCommentInfo_whenSearchingArticleComments_thenReturnsComments() {
        //given
        ArticleCommentDto dto = ArticleCommentDto.of(LocalDateTime.now(), "createdBy", LocalDateTime.now(), "modifiedBy", "content");
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);
        //when
        sut.saveArticleComment(dto);
        //then
        then(articleCommentRepository).should(times(1)).save(any(ArticleComment.class));
    }

}