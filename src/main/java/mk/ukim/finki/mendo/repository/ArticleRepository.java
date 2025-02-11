package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.Article;
import mk.ukim.finki.mendo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
