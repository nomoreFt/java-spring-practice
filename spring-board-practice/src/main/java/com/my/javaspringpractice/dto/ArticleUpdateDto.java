package com.my.javaspringpractice.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.my.javaspringpractice.domain.Article}
 */
public record ArticleUpdateDto(
        String title,
        String content,
        String hashtag
) {
    public static ArticleUpdateDto of(String title, String content, String hashtag) {
        return new ArticleUpdateDto(title, content, hashtag);
    }
}