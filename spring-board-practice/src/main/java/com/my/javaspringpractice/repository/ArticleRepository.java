package com.my.javaspringpractice.repository;

import com.my.javaspringpractice.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}