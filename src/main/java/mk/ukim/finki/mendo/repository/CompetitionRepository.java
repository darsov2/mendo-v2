package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}
