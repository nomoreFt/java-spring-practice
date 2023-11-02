package com.my.javaspringpractice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@ToString
@Embeddable
public class UserId implements Serializable {

    @Getter
    @Column(nullable = false, length = 100)
    private String userId;

    protected UserId() {}

    private UserId(String userId) {
        this.userId = userId;
    }

    public static UserId of(String userId) {
        return new UserId(userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserId userId1)) return false;
        return Objects.equals(userId, userId1.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
