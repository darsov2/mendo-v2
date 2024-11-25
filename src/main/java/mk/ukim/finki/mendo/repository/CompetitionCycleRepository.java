package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.CompetitionCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionCycleRepository extends JpaRepository<CompetitionCycle, Long> {
}
