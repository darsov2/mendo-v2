package mk.ukim.finki.mendo.service;

import mk.ukim.finki.mendo.model.Article;
import java.util.List;


public interface ArticleService {

    Article findById(Long articleId);
    List<Article> findAll();
    Article create(String title, String text);
    Article update(Long id, String title, String text);
    Article delete(Long articleId);

}
