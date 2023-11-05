package com.my.javaspringpractice.repository;

import com.my.javaspringpractice.config.TestJpaConfig;
import com.my.javaspringpractice.domain.Article;
import com.my.javaspringpractice.domain.UserAccount;
import com.my.javaspringpractice.domain.UserId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("JPA 연결 테스트")
@Import(TestJpaConfig.class)
@DataJpaTest
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;


    @Test
    void test(){
        Iterable<Article> all = articleRepository.findAll();
        assertThat(all).isNotNull();

        UserAccount userAccount = UserAccount.of(UserId.of("unoTest"), "pw", "email", "name", "memo");
        UserAccount save = userAccountRepository.save(userAccount);
        assertThat(save).isNotNull();
        assertThat(save.getUserId()).isEqualTo(userAccount.getUserId());
    }


}