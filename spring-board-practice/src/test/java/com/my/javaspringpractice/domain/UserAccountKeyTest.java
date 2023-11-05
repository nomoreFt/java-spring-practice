package com.my.javaspringpractice.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("UserAccount 테스트에서")
public class UserAccountKeyTest {


    @DisplayName("UserId가 같으면 같은 UserAccount로 취급한다.")
    @Test
    void givenSameUserIdAccount_whenEquals_thenEquals(){
        //given
        UserAccount userAccount = UserAccount.of(UserId.of("admin"), "password", "email", "nickname", "memo");
        UserAccount userAccount2 = UserAccount.of(UserId.of("admin"), "password2", "email", "nickname", "memo");
        UserId userId = UserId.of("admin");

        //when

        //then
        Assertions.assertThat(userAccount.getUserId()).isEqualTo(userId);
        Assertions.assertThat(userAccount).isEqualTo(userAccount2);
    }
}
