package com.my.javaspringpractice.dto;

import com.my.javaspringpractice.domain.Article;
import com.my.javaspringpractice.domain.ArticleComment;
import com.my.javaspringpractice.domain.UserAccount;

import java.time.LocalDateTime;

public record ArticleCommentDto(
        Long id,
        Long articleId,
        String content,
        UserAccountDto userAccountDto,

        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static ArticleCommentDto of(Long articleId, String content, UserAccountDto userAccountDto) {
        return new ArticleCommentDto(null, articleId, content, userAccountDto, null, null, null, null);
    }

    public static ArticleCommentDto of(Long id, Long articleId, String content, UserAccountDto userAccountDto, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ArticleCommentDto(id, articleId, content, userAccountDto, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ArticleCommentDto from(ArticleComment entity){
        return new ArticleCommentDto(
                entity.getId(),
                entity.getArticle().getId(),
                entity.getContent(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public ArticleComment toEntity(Article article, UserAccount userAccount){
        return ArticleComment.of(
                content,
                article,
                userAccount
        );
    }
}
