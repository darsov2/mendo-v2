package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.model.Article;
import mk.ukim.finki.mendo.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String showArticles(Model model) {
        List<Article> articles = articleService.findAll();
        model.addAttribute("articles", articles);
        return "articles";
    }

    @GetMapping("/add")
    public String showAddArticleForm(Model model) {
        model.addAttribute("bodyContent", "admin/addArticle");
        return "master";
    }

    @PostMapping("/add")
    public String addArticle(@RequestParam String title, @RequestParam String text) {
        articleService.create(title, text);
        return "redirect:/articles";
    }

    @GetMapping("/edit/{id}")
    public String showEditArticleForm(@PathVariable Long id, Model model) {
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
        articleService.update(id, title, text);
        return "redirect:/articles";
    }

    @PostMapping("/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleService.delete(id);
        return "redirect:/articles";
    }
}
