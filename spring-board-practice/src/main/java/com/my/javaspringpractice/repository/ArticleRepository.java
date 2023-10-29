package com.my.javaspringpractice.repository;

import com.my.javaspringpractice.domain.Article;
import com.my.javaspringpractice.domain.QArticle;
import com.my.javaspringpractice.repository.querydsl.ArticleRepositoryCustom;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        ArticleRepositoryCustom,
        QuerydslPredicateExecutor<Article>,//기본적으로 Entity 안에 있는 검색기능을 추가해준다.
        QuerydslBinderCustomizer<QArticle> {

        Page<Article> findByTitleContaining(String title, Pageable pageable);
        Page<Article> findByContentContaining(String content, Pageable pageable);
        Page<Article> findByUserAccount_UserIdContaining(String userId, Pageable pageable);
        Page<Article> findByUserAccount_NicknameContaining(String nickname, Pageable pageable);
        Page<Article> findByHashtag(String hashtag, Pageable pAgeable);

        @Override
        default void customize(QuerydslBindings bindings, QArticle root){
                bindings.excludeUnlistedProperties(true);//기본적으로 제공되는 기능을 제외한다.
                bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);//원하는 검색 매칭을 설정한다.
                //bindings.bind(root.title).first(StringExpression::likeIgnoreCase);//title에 대한 검색을 like '${v}' 정확한 일치
                bindings.bind(root.title).first(StringExpression::containsIgnoreCase);//title에 대한 검색을 like '%${v}%' 부분일치 가능
                bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
                bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
                bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
                bindings.bind(root.createdAt).first(DateTimeExpression::eq);//시간까지 정확히 일치해야 한다. 그래서 바꿔야함




        }
}