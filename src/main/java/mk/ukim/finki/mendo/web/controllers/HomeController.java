package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.model.Article;
import mk.ukim.finki.mendo.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final ArticleService articleService;

    public HomeController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("")
    public String home(Model model) {
        List<Article> articles = articleService.findAll().stream()
                .sorted(Comparator.comparing(Article::getDateCreated).reversed())
                .limit(6)
                .collect(Collectors.toList());
        model.addAttribute("articles", articles);
        model.addAttribute("bodyContent","home");
        return "master";
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("bodyContent","register");
        return "master";
    }
}
