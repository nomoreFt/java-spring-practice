package com.my.javaspringpractice.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

public class SecurityUtils {

    public static void runAs(String username, String password, String... roles) {
        Assert.notNull(username, "username cannot be null");
        Assert.notNull(password, "password cannot be null");

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(username, password, AuthorityUtils.createAuthorityList(roles))
        );
    }
}
