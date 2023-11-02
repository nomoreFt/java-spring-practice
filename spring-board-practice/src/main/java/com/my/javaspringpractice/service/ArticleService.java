package com.my.javaspringpractice.service;

import com.my.javaspringpractice.domain.type.SearchType;
import com.my.javaspringpractice.dto.ArticleDto;
import com.my.javaspringpractice.dto.ArticleWithCommentDto;
import com.my.javaspringpractice.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword,
                                           Pageable pageable) {
        return Page.empty();
    }

    public ArticleWithCommentDto getArticleWithComments(Long articleId) {
        return null;
    }

    public long getArticleCount() {
        return articleRepository.count();
    }

    public Page<ArticleDto> searchArticlesViaHashtag(String searchValue, Pageable pageable) {
    }

    public List<String> getHashTags() {
        return articleRepository.findAllDistinctHashtags();
    }
}
