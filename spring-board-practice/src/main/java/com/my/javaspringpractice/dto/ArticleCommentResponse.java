package com.my.javaspringpractice.dto;

import java.time.LocalDateTime;

public record ArticleCommentResponse(
        Long id,
        Long articleId,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static ArticleCommentResponse of(Long id, Long articleId, String content, LocalDateTime createdAt,
                                            String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ArticleCommentResponse(id, articleId, content, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ArticleCommentResponse from(ArticleCommentDto dto){
        return new ArticleCommentResponse(
                dto.id(),
                dto.articleId(),
                dto.content(),
                dto.createdAt(),
                dto.createdBy(),
                dto.modifiedAt(),
                dto.modifiedBy()
        );
    }

}
