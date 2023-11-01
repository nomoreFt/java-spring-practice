package com.my.javaspringpractice.service;

import com.my.javaspringpractice.domain.Article;
import com.my.javaspringpractice.dto.ArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    public Page<ArticleDto> searchArticles(Object eq, Object eq1, org.springframework.data.domain.Pageable any) {
        return Page.empty();
    }
}
