package com.my.javaspringpractice.ui;


import com.my.javaspringpractice.domain.type.FormStatus;
import com.my.javaspringpractice.domain.type.SearchType;
import com.my.javaspringpractice.dto.request.ArticleRequest;
import com.my.javaspringpractice.dto.response.ArticleResponse;
import com.my.javaspringpractice.dto.response.ArticleWithCommentResponse;
import com.my.javaspringpractice.dto.security.BoardPrincipal;
import com.my.javaspringpractice.service.ArticleService;
import com.my.javaspringpractice.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, ModelMap map){
        ArticleWithCommentResponse article = ArticleWithCommentResponse.from(
                articleService.getArticleWithComments(articleId));

        map.addAttribute("article", article);
        map.addAttribute("articleComments", article.articleCommentsResponse());
        map.addAttribute("totalCount", articleService.getArticleCount());

        return "articles/detail";
    }

    @GetMapping("/search-hashtag")
    public String searchArticleHashtag(@RequestParam(required = false) String searchValue,
                                       @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                       ModelMap map) {
        Page<ArticleResponse> articles = articleService.searchArticlesViaHashtag(searchValue, pageable).map(ArticleResponse::from);
        List<Integer> paginationBarNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), articles.getTotalPages());
        List<String> hashTags = articleService.getHashTags();

        map.addAttribute("articles", articles);
        map.addAttribute("paginationBarNumbers", paginationBarNumbers);
        map.addAttribute("searchType", SearchType.HASHTAG);

        return "articles/search-hashtag";
    }

    @GetMapping("/form")
    public String articleForm(ModelMap map) {
        map.addAttribute("formStatus", FormStatus.CREATE);
        return "articles/form";
    }

    @PostMapping("/form")
    public String createArticle(
            @AuthenticationPrincipal BoardPrincipal principal,
            @RequestBody ArticleRequest articleRequest
            ){

        articleService.saveArticle(articleRequest.toDto(principal.toDto()));
        return "redirect:/articles";
    }

    @PostMapping("{articleId}/form")
    public String updateArticleForm(@PathVariable Long articleId,
                                    @AuthenticationPrincipal BoardPrincipal boardPrincipal,
                                    @RequestBody ArticleRequest articleRequest) {
        articleService.updateArticle(articleId, articleRequest.toDto(boardPrincipal.toDto()));
        return "redirect:/articles/" + articleId;
    }

    @PostMapping("/{articleId}/delete")
    public String deleteArticle(@PathVariable Long articleId,
                                @AuthenticationPrincipal BoardPrincipal boardPrincipal) {
        articleService.deleteArticle(articleId, boardPrincipal.userId());
        return "redirect:/articles";
    }

}
