package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.model.Article;
import mk.ukim.finki.mendo.model.enums.Grade;
import mk.ukim.finki.mendo.service.ArticleService;
import mk.ukim.finki.mendo.web.mapper.SchoolMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final ArticleService articleService;
    private final SchoolMapper schoolMapper;

    public HomeController(ArticleService articleService, SchoolMapper schoolMapper) {
        this.articleService = articleService;
        this.schoolMapper = schoolMapper;
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
        model.addAttribute("schools", schoolMapper.listSchools());
        model.addAttribute("grades", Grade.values());
        return "master";
    }
}
