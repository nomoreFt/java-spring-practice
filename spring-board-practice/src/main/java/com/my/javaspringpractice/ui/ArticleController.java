package com.my.javaspringpractice.ui;


import com.my.javaspringpractice.domain.type.SearchType;
import com.my.javaspringpractice.dto.ArticleResponse;
import com.my.javaspringpractice.service.ArticleService;
import com.my.javaspringpractice.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.data.domain.Sort.*;


@RequiredArgsConstructor
@RequestMapping("/articles")
@Controller
public class ArticleController {
    private final ArticleService articleService;
    private final PaginationService paginationService;


    @GetMapping
    public String articles(@RequestParam(required = false) SearchType searchType,
                           @RequestParam(required = false) String searchValue,
                           @PageableDefault(size = 10, sort = "createdAt", direction = Direction.DESC) Pageable pageable,
                           ModelMap map) {

        Page<ArticleResponse> articles = articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from);
        List<Integer> paginationBarNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), articles.getTotalPages());

        map.addAttribute("articles", articles);
        map.addAttribute("paginationBarNumbers", paginationBarNumbers);
        map.addAttribute("searchTypes", SearchType.values());
        return "articles/index";
    }


}
