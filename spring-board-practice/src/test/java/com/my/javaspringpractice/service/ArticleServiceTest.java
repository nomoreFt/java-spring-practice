package com.my.javaspringpractice.service;

import com.my.javaspringpractice.domain.Article;
import com.my.javaspringpractice.domain.UserAccount;
import com.my.javaspringpractice.domain.UserId;
import com.my.javaspringpractice.domain.type.SearchType;
import com.my.javaspringpractice.dto.ArticleDto;
import com.my.javaspringpractice.dto.ArticleWithCommentDto;
import com.my.javaspringpractice.dto.UserAccountDto;
import com.my.javaspringpractice.repository.ArticleRepository;
import com.my.javaspringpractice.repository.UserAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    private ArticleService sut;
    @Mock
    ArticleRepository articleRepository;
    @Mock
    UserAccountRepository userAccountRepository;


    @DisplayName("검색어 없이 게시글을 검색하면, 게시글 페이지를 반환한다.")
    @Test
    void givenNoSearchParameters_whenSearchingArticle_thenReturnsArticlePage() {
        // Given
        Pageable pageable = PageRequest.ofSize(10);
        given(articleRepository.findAll(pageable)).willReturn(Page.empty());

        // When
        Page<ArticleDto> articles = sut.searchArticles(null, null, pageable);

        // Then
        articles.isEmpty();
        then(articleRepository).should().findAll(pageable);
    }

    @DisplayName("검색어와 검색타입을 넣고 게시글을 검색하면, 게시글 페이지를 반환한다.")
    @Test
    void givenWithSearchParameters_whenSearchingArticle_thenReturnsArticlePage() {
        // Given
        SearchType searchType = SearchType.TITLE;
        String searchValue = "title";
        Pageable pageable = PageRequest.ofSize(10);
        given(articleRepository.findByTitleContaining(searchValue, pageable)).willReturn(Page.empty());

        // When
        Page<ArticleDto> articles = sut.searchArticles(searchType, searchValue, pageable);

        // Then
        assertThat(articles).isEmpty();
        then(articleRepository).should().findByTitleContaining(searchValue, pageable);
    }

    @DisplayName("게시글 ID로 게시글을 조회하면 게시글과 댓글DTO를 반환한다.")
    @Test
    void givenArticleId_whenSearchingArticle_thenReturnsArticleWithCommentDto() {
        // Given
        Long articleId = 1L;
        Article article = createArticle(articleId);
        given(articleRepository.findById(articleId)).willReturn(Optional.of(article));

        // When
        ArticleWithCommentDto result = sut.getArticleWithComments(articleId);

        // Then
        assertThat(result)
                .hasFieldOrPropertyWithValue("title", article.getTitle())
                .hasFieldOrPropertyWithValue("content", article.getContent())
                .hasFieldOrPropertyWithValue("hashtag", article.getHashtag());
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("없는 게시글 ID로 게시글을 조회하면 EntityNotFoundException을 던진다.")
    @Test
    void givenNonexistentArticleId_whenSearchingArticle_thenThrowsEntityNotFoundException() {
        // Given
        Long articleId = 1L;
        given(articleRepository.findById(articleId)).willReturn(Optional.empty());

        // When
        Throwable t = catchThrowable(() -> sut.getArticleWithComments(articleId));

        // Then
        assertThat(t).isInstanceOf(EntityNotFoundException.class);
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("게시글의 개수를 반환한다.")
    @Test
    void givenNothing_whenSearchingArticleCount_thenReturnsArticleCount() {
        //given
        long expect = 1L;
        given(articleRepository.count()).willReturn(expect);
        //when
        long actual = sut.getArticleCount();

        //then
        assertThat(actual).isEqualTo(expect);
        then(articleRepository).should().count();
    }

    @DisplayName("해시태그로 게시글을 검색하면, 게시글 페이지를 반환한다.")
    @Test
    void givenHashtag_whenSearchingArticle_thenReturnsArticlePage() {
        //given
        String hashtag = "#java";
        Pageable pageable = PageRequest.ofSize(10);
        given(articleRepository.findByHashtag(hashtag, pageable)).willReturn(Page.empty());

        //when
        Page<ArticleDto> actual = sut.searchArticlesViaHashtag(hashtag, pageable);

        //then
        Assertions.assertThat(actual).isEmpty();
        then(articleRepository).should().findByHashtag(hashtag, pageable);

    }

    @DisplayName("빈 값을 해시태그로 넘기면, 빈 페이지를 반환한다.")
    @Test
    void givenNothing_whenSearchingArticleViaHashtag_thenReturnsEmptyPage() {
        //given
        Pageable pageable = PageRequest.ofSize(10);

        //when
        Page<ArticleDto> actual = sut.searchArticlesViaHashtag(null, pageable);

        //then
        Assertions.assertThat(actual).isEqualTo(Page.empty(pageable));
        then(articleRepository).shouldHaveNoInteractions();
    }

    @DisplayName("중복없는 해시태그 리스트를 반환한다.")
    @Test
    void givenNothing_whenSearchingHashtags_thenReturnsDistinctHashtagList() {
        //given
        List<String> expect = List.of("#java", "#spring", "#jpa");
        given(articleRepository.findAllDistinctHashtags()).willReturn(expect);

        //when
        List<String> actual = sut.getHashTags();

        //then

        Assertions.assertThat(actual).isEqualTo(expect);
        then(articleRepository).should().findAllDistinctHashtags();
    }

    @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다.")
    @Test
    void givenArticleInfo_whenSaveArticle_ReturnsNothing() {
        //given
        ArticleDto articleDto = createArticleDto("title", "content", "hashtag");
        given(userAccountRepository.getReferenceById(articleDto.userAccountDto().userId())).willReturn(createUserAccount());
        given(articleRepository.save(any(Article.class))).willReturn(createArticle(1L));
        //when
        sut.saveArticle(articleDto);

        //then
        then(userAccountRepository).should().getReferenceById(articleDto.userAccountDto().userId());
        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글의 수정 정보를 입력하면, 게시글을 수정한다.")
    @Test
    void givenModifiedArticleInfo_whenUpdatingArticle_thenUpdatesArticle(){
        //given
        Article article = createArticle(1L);
        ArticleDto dto = createArticleDto("title", "content", "hashtag");
        given(articleRepository.getReferenceById(dto.id())).willReturn(article);
        given(userAccountRepository.getReferenceById(dto.userAccountDto().userId())).willReturn(createUserAccount());

        //when
        sut.updateArticle(dto.id(), dto);

        //then
        Assertions.assertThat(article)
                        .hasFieldOrPropertyWithValue("title", dto.title())
                        .hasFieldOrPropertyWithValue("content", dto.content())
                        .hasFieldOrPropertyWithValue("hashtag", dto.hashtag());
        then(articleRepository).should().getReferenceById(dto.id());
        then(userAccountRepository).should().getReferenceById(dto.userAccountDto().userId());

    }

    @DisplayName("게시글의 ID를 입력하면, 게시글을 삭제한다")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeletesArticle(){
        //given
        Long articleId = 1L;
        UserId userId = createUserId();
        willDoNothing().given(articleRepository).deleteById(articleId);
        given(articleRepository.getReferenceById(articleId)).willReturn(createArticle(articleId));
        given(userAccountRepository.getReferenceById(userId)).willReturn(createUserAccount());
        //when
        sut.deleteArticle(articleId, userId);

        //then
        then(articleRepository).should().getReferenceById(articleId);
        then(userAccountRepository).should().getReferenceById(userId);
        then(articleRepository).should().deleteById(articleId);
    }

    private ArticleDto createArticleDto(String title, String content,String hashtag) {
        return ArticleDto.of(
                1L,
                createUserAccountDto(),
                title,
                content,
                hashtag,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "createdBy",
                "updatedBy");
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                createUserId(),
                "password",
                "nickname",
                "email",
                "memo",
                LocalDateTime.now(),
                "createdBy",
                LocalDateTime.now(),
                "updatedBy"
        );
    }


    private Article createArticle(Long articleId) {
        return Article.of(
                "title",
                "content",
                "hashtag",
                createUserAccount());
    }

    private UserAccount createUserAccount() {
        return UserAccount.of(
                createUserId(),
                "password",
                "nickname",
                "email",
                "memo");
    }

    private static UserId createUserId() {
        return UserId.of("userId");
    }


}