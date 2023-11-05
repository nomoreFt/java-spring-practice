package com.my.javaspringpractice.service;

import com.my.javaspringpractice.domain.type.SearchType;

public class ArticleSearchValidation{
    private final SearchType searchType;
    private final String searchKeyword;

    private ArticleSearchValidation(SearchType searchType, String searchKeyword){
        this.searchType = searchType;
        this.searchKeyword = searchKeyword;
    }

    public boolean isSearchKeywordEmpty(){
        return searchKeyword == null || searchKeyword.isBlank();
    }

    public static ArticleSearchValidation of(SearchType searchType, String searchKeyword){
        return new ArticleSearchValidation(searchType, searchKeyword);
    }

}
