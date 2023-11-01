package com.my.javaspringpractice.repository;

import com.my.javaspringpractice.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends CrudRepository<Article,Long> {
}
