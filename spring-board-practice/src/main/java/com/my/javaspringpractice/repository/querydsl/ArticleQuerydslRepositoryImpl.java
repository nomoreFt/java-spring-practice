package com.my.javaspringpractice.repository.querydsl;

import com.my.javaspringpractice.domain.Article;
import com.my.javaspringpractice.domain.QArticle;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ArticleQuerydslRepositoryImpl extends QuerydslRepositorySupport implements ArticleQuerydslRepository {
    public ArticleQuerydslRepositoryImpl() {
        super(Article.class);
    }


    @Override
    public List<String> findAllDistinctHashtags() {
        QArticle article = QArticle.article;

        return from(article)
                .distinct()
                .select(article.hashtag)
                .where(article.hashtag.isNotNull())
                .fetch();
    }
}

