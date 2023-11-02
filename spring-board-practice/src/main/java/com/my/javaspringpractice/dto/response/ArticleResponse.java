package com.my.javaspringpractice.dto.response;

import com.my.javaspringpractice.dto.ArticleDto;

import java.time.LocalDateTime;

public record ArticleResponse(
        Long id,
        String title,
        String content,
        String nickname,
        String hashtag,
        String email,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {
    public static ArticleResponse of(Long id, String title, String content, String nickname, String hashtag, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new ArticleResponse(id, title, content, nickname, hashtag, email, createdAt, modifiedAt);
    }

    public static ArticleResponse from(ArticleDto dto) {
        return new ArticleResponse(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.userAccountDto().nickname(),
                dto.hashtag(),
                dto.userAccountDto().email(),
                dto.createdAt(),
                dto.modifiedAt()
        );
    }
}
