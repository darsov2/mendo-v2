package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
