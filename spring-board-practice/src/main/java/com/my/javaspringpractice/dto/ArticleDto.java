package com.my.javaspringpractice.dto;

import com.my.javaspringpractice.domain.Article;
import com.my.javaspringpractice.domain.UserAccount;

import java.time.LocalDateTime;

public record ArticleDto(
        Long id,
        UserAccountDto userAccountDto,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy) {

    public static ArticleDto of(UserAccountDto userAccountDto, String title, String content, String hashtag) {
        return new ArticleDto(null, userAccountDto, title, content, hashtag, null, null, null, null);
    }

    public static ArticleDto of(Long id, UserAccountDto userAccountDto, String title, String content, String hashtag, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy) {
        return new ArticleDto(id, userAccountDto, title, content, hashtag, createdAt, modifiedAt, createdBy, modifiedBy);
    }

    public static ArticleDto from(Article entity){
        return new ArticleDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                entity.getCreatedAt(),
                entity.getModifiedAt(),
                entity.getCreatedBy(),
                entity.getModifiedBy()
        );
    }

    public Article toEntity(UserAccount userAccount) {
        return Article.of(
                title,
                content,
                hashtag,
                userAccount
        );
    }
}
