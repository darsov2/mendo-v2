package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.CompetitionTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionTaskRepository extends JpaRepository<CompetitionTask, Long> {
}
