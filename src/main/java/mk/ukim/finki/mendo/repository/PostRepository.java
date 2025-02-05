package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByThread_Id(Long threadId);
}
