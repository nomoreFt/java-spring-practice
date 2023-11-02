package com.my.javaspringpractice.dto.response;

import com.my.javaspringpractice.dto.ArticleWithCommentDto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleWithCommentResponse(
        Long id,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String email,
        String nickname,
        String userId,
        Set<ArticleCommentResponse> articleCommentsResponse
) {
    public static ArticleWithCommentResponse of(Long id, String title, String content, String hashtag, LocalDateTime createdAt, String email, String nickname, String userId, Set<ArticleCommentResponse> articleCommentResponses) {
        return new ArticleWithCommentResponse(id, title, content, hashtag, createdAt, email, nickname, userId, articleCommentResponses);
    }

    public static ArticleWithCommentResponse from(ArticleWithCommentDto dto){
        return new ArticleWithCommentResponse(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.hashtag(),
                dto.createdAt(),
                dto.userAccountDto().email(),
                dto.userAccountDto().nickname(),
                dto.userAccountDto().userId().getUserId(),
                dto.articleCommentDtos().stream()
                        .map(ArticleCommentResponse::from)
                        .collect(Collectors.toSet())
        );


    }
}
