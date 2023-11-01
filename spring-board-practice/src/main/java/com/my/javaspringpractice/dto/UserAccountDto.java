package com.my.javaspringpractice.dto;

import com.my.javaspringpractice.domain.UserAccount;
import com.my.javaspringpractice.domain.UserId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.my.javaspringpractice.domain.UserAccount}
 */
public record UserAccountDto(
        UserId userId,
        String userPassword,
        String email,
        String nickname,
        String memo,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) implements Serializable {
    public static UserAccountDto of(UserId userId, String userPassword, String email, String nickname, String memo, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new UserAccountDto(userId, userPassword, email, nickname, memo, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static UserAccountDto of(String userPassword, String email, String nickname, String memo) {
        return new UserAccountDto(null, userPassword, email, nickname, memo, null, null, null, null);
    }

    public static UserAccountDto from(UserAccount dto) {
        return new UserAccountDto(
                dto.getUserId(),
                dto.getUserPassword(),
                dto.getEmail(),
                dto.getNickname(),
                dto.getMemo(),
                dto.getCreatedAt(),
                dto.getCreatedBy(),
                dto.getModifiedAt(),
                dto.getModifiedBy()
        );
    }
}