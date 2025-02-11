package mk.ukim.finki.mendo.service.impl;

import mk.ukim.finki.mendo.model.Article;
import mk.ukim.finki.mendo.repository.ArticleRepository;
import mk.ukim.finki.mendo.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Article findById(Long articleId) {
        return articleRepository.findById(articleId).orElse(null);
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }


    @Override
    public Article create(String title, String text) {
        Article article = new Article(title, text);
        return articleRepository.save(article);
    }

    @Override
    public Article update(Long articleId, String title, String text) {
        Article article = findById(articleId);
        article.setTitle(title);
        article.setText(text);
        return articleRepository.save(article);
    }

    @Override
    public Article delete(Long articleId) {
        Article article = findById(articleId);
        articleRepository.delete(article);
        return article;
    }
}
