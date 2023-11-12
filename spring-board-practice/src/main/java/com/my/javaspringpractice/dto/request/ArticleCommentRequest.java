package com.my.javaspringpractice.dto.request;

import com.my.javaspringpractice.dto.ArticleCommentDto;
import com.my.javaspringpractice.dto.UserAccountDto;

public record ArticleCommentRequest(Long articleId, String content) {

    public static ArticleCommentRequest of(Long articleId, String content) {
        return new ArticleCommentRequest(articleId, content);
    }

    public ArticleCommentDto toDto(UserAccountDto userAccountDto) {
        return ArticleCommentDto.of(
                articleId,
                content,
                userAccountDto
        );
    }
}
