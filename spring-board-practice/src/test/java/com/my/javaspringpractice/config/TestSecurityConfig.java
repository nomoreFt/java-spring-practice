package com.my.javaspringpractice.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

@Import(SecurityConfig.class)
@TestConfiguration
public class TestSecurityConfig {
}
