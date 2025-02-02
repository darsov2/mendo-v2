package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.Category;
import mk.ukim.finki.mendo.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findAllByCategory(Category category);
}
