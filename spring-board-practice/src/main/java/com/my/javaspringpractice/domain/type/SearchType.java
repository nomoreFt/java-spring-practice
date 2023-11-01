package com.my.javaspringpractice.domain.type;

import lombok.Getter;

public enum SearchType {
    TITLE("제목"),
    CONTENT("본문"),
    ID("유저 ID"),
    NICKNAME("닉네임"),
    HASHTAG("해시태그");

    private final String description;

    public String getDescription() {
        return description;
    }

    SearchType(String description) {
        this.description = description;
    }

}
