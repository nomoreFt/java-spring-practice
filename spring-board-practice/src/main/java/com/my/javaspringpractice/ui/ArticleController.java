package com.my.javaspringpractice.ui;


import com.my.javaspringpractice.service.ArticleService;
import com.my.javaspringpractice.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RequiredArgsConstructor
@Controller
public class ArticleController {
    private final ArticleService articleService;
    private final PaginationService paginationService;

    @GetMapping("/articles")
    public String list() {
        articleService.searchArticles(null, null, Pageable.ofSize(10));
        return "articles/index";
    }


}
