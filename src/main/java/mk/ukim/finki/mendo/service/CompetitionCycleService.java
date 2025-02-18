package mk.ukim.finki.mendo.service;

import mk.ukim.finki.mendo.model.CompetitionCycle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CompetitionCycleService {
    CompetitionCycle addCycle(String name, LocalDate year, LocalDateTime registrationDeadline);
    CompetitionCycle findById(Long id);
    List<CompetitionCycle> findAllSortedByYearDesc();
    CompetitionCycle create(String name, LocalDate year, LocalDateTime registrationDeadline);
    void deleteById(Long id);
    Optional<CompetitionCycle> update(Long id, String name, LocalDate year, LocalDateTime registrationDeadline);
    List<CompetitionCycle> findAll();
    List<CompetitionCycle> findAllUpcoming();
}
