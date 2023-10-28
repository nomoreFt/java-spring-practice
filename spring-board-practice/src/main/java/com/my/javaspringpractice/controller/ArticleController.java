package com.my.javaspringpractice.controller;

import com.my.javaspringpractice.domain.type.SearchType;
import com.my.javaspringpractice.dto.ArticleResponse;
import com.my.javaspringpractice.dto.ArticleWithCommentsDto;
import com.my.javaspringpractice.dto.ArticleWithCommentsResponse;
import com.my.javaspringpractice.service.ArticleService;
import com.my.javaspringpractice.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/articles")
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final PaginationService  paginationService;

    @GetMapping
    public String articles(@RequestParam(required = false) SearchType searchType,
                           @RequestParam(required = false) String searchValue,
                           @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                           ModelMap map
    ){
        Page<ArticleResponse>  articles = articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from);
        int totalPages = articles.getTotalPages();
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), totalPages);

        map.addAttribute("articles", articles);
        map.addAttribute("paginationBarNumbers", barNumbers);
        map.addAttribute("searchTypes", SearchType.values());
        return "articles/index";
    }

    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, ModelMap map) {
        ArticleWithCommentsDto dto = articleService.getArticle(articleId);
        ArticleWithCommentsResponse article = ArticleWithCommentsResponse.from(dto);
        map.addAttribute("article", article);
        map.addAttribute("articleComments", article.articleCommentsResponses());
        return "articles/detail";
    }

}
