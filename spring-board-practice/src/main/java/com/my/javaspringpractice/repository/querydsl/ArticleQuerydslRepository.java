package com.my.javaspringpractice.repository.querydsl;

import java.util.List;

public interface ArticleQuerydslRepository {
    List<String> findAllDistinctHashtags();
}
