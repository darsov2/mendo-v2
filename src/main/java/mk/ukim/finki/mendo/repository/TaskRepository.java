package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.Category;
import mk.ukim.finki.mendo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByCategory(Category category);
}
