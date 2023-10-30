package com.my.javaspringpractice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "email"),
        @Index(columnList = "nickname"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class UserAccount extends BaseEntity{

    @EmbeddedId private UserId userId;

    @Setter @Column(nullable = false) private String userPassword;
    @Setter @Column(nullable = false, unique = true, length = 100) private String email;
    @Setter @Column(nullable = false, unique = true, length = 100) private String nickname;
    @Setter private String memo;

    protected UserAccount() {}

    private UserAccount(String userId, String userPassword, String email, String nickname, String memo) {
        this.userId = UserId.of(userId);
        this.userPassword = userPassword;
        this.email = email;
        this.nickname = nickname;
        this.memo = memo;
    }

    public static UserAccount of(String userId, String userPassword, String email, String nickname, String memo) {
        return new UserAccount(userId, userPassword, email, nickname, memo);
    }

}
