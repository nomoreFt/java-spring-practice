package com.my.javaspringpractice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Table(name = "user_account", indexes = {
        @Index(columnList = "email", unique = true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")

})
@Entity
public class UserAccount extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, length = 50)
    private String userId;

    @Column(nullable = false)
    private String userPassword;

    @Setter
    @Column(length = 100)
    private String email;

    @Setter
    @Column(length = 100)
    private String nickname;

    @Setter
    private String memo;

    protected UserAccount() {
    }

    private UserAccount(String userId, String userPassword,String mail, String nickname, String memo) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = mail;
        this.nickname = nickname;
        this.memo = memo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount that)) return false;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    public static UserAccount of(String userId, String userPassword,String email, String nickname, String memo) {
        return new UserAccount(userId, userPassword, email, nickname, memo);
    }

}
