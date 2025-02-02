package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}
