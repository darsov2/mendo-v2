package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.CompetitionCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CompetitionCycleRepository extends JpaRepository<CompetitionCycle, Long> {
    List<CompetitionCycle> findAllByOrderByYearDesc();
    boolean existsByNameAndYear(String name, LocalDate year);
    List<CompetitionCycle> findAllByYearAfter(LocalDate year);
}
