package mk.ukim.finki.mendo.service;

import mk.ukim.finki.mendo.model.CompetitionCycle;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface CompetitionCycleService {
    CompetitionCycle addCycle(String name, LocalDate year, LocalDateTime registrationDeadline);
    CompetitionCycle findById(Long id);
}
