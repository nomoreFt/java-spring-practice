package com.my.javaspringpractice.repository;

import com.my.javaspringpractice.domain.Article;
import com.my.javaspringpractice.repository.querydsl.ArticleQuerydslRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface ArticleRepository extends JpaRepository<Article,Long>, ArticleQuerydslRepository {
    Page<Article> findByTitleContaining(String searchKeyword, Pageable pageable);

    Page<Article> findByContentContaining(String searchKeyword, Pageable pageable);

    Page<Article> findByUserAccount_UserIdContaining(String searchKeyword, Pageable pageable);

    Page<Article> findByUserAccount_NicknameContaining(String searchKeyword, Pageable pageable);

    Page<Article> findByHashtag(String searchKeyword, Pageable pageable);
}
