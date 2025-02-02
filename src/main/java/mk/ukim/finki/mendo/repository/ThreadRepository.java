package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.Task;
import mk.ukim.finki.mendo.model.Thread;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThreadRepository extends JpaRepository<Thread, Long> {

}
