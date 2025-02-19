package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.model.Article;
import mk.ukim.finki.mendo.service.ArticleService;
import mk.ukim.finki.mendo.service.AuthorizationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final AuthorizationService authorizationService;

    public ArticleController(ArticleService articleService, AuthorizationService authorizationService) {
        this.articleService = articleService;
        this.authorizationService = authorizationService;
    }

    @GetMapping
    public String showArticles(Model model) {
        authorizationService.canViewArticles();

        List<Article> articles = articleService.findAll();
        model.addAttribute("articles", articles);
        model.addAttribute("bodyContent", "articles");
        return "master";
    }

    @GetMapping("/add")
    public String showAddArticleForm(Model model) {
        authorizationService.canManageArticles();

        model.addAttribute("bodyContent", "admin/addArticle");
        return "master";
    }

    @PostMapping("/add")
    public String addArticle(@RequestParam String title, @RequestParam String text) {
        authorizationService.canManageArticles();

        articleService.create(title, text);
        return "redirect:/articles";
    }

    @GetMapping("/edit/{id}")
    public String showEditArticleForm(@PathVariable Long id, Model model) {
        authorizationService.canManageArticles();

        Article article = articleService.findById(id);
        if (article == null) {
            return "redirect:/articles";
        }
        model.addAttribute("article", article);
        model.addAttribute("bodyContent", "admin/edit-article");
        return "master";
    }

    @PostMapping("/edit/{id}")
    public String editArticle(@PathVariable Long id, @RequestParam String title, @RequestParam String text) {
        authorizationService.canManageArticles();

        articleService.update(id, title, text);
        return "redirect:/articles";
    }

    @PostMapping("/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        authorizationService.canManageArticles();

        articleService.delete(id);
        return "redirect:/articles";
    }

    @GetMapping("/{id}")
    public String showArticleDetails(@PathVariable Long id, Model model) {
        authorizationService.canViewArticles();

        Article article = articleService.findById(id);
        if (article == null) {
            return "redirect:/articles";
        }
        model.addAttribute("article", article);
        return "article-details";
    }
}
