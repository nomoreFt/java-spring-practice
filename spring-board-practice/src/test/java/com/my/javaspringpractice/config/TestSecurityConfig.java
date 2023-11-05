package com.my.javaspringpractice.config;

import com.my.javaspringpractice.domain.UserAccount;
import com.my.javaspringpractice.domain.UserId;
import com.my.javaspringpractice.repository.UserAccountRepository;
import org.mockito.BDDMockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;

@Import(SecurityConfig.class)
@TestConfiguration
public class TestSecurityConfig {

    @MockBean
    private UserAccountRepository userAccountRepository;


    @BeforeTestMethod
    public void securitySetUp() {
        BDDMockito.given(userAccountRepository.findById(UserId.of(anyString()))).willReturn(Optional.of(UserAccount.of(
                UserId.of("userId"),
                "password",
                "email",
                "nickname",
                "memo"
        )));
    }

}
