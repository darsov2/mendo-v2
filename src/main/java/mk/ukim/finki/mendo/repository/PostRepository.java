package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
