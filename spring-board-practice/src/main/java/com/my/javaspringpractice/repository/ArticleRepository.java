package com.my.javaspringpractice.repository;

import com.my.javaspringpractice.domain.Article;
import com.my.javaspringpractice.domain.QArticle;
import com.my.javaspringpractice.repository.querydsl.ArticleQuerydslRepository;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@PreAuthorize("hasRole('USER')")
@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article,Long>,
        ArticleQuerydslRepository,
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle> {

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    long count();

    @PreAuthorize("hasRole('ADMIN')")
    Page<Article> findByTitleContaining(String searchKeyword, Pageable pageable);

    Page<Article> findByContentContaining(String searchKeyword, Pageable pageable);

    Page<Article> findByUserAccount_UserIdContaining(String searchKeyword, Pageable pageable);

    Page<Article> findByUserAccount_NicknameContaining(String searchKeyword, Pageable pageable);

    Page<Article> findByHashtag(String searchKeyword, Pageable pageable);

    @Override
    default void customize(QuerydslBindings bindings, QArticle root){
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}
