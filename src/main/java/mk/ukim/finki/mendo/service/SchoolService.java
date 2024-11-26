package mk.ukim.finki.mendo.service;

import mk.ukim.finki.mendo.model.CompetitionCycle;
import mk.ukim.finki.mendo.model.School;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SchoolService {
    List<School> findAll();

    Optional<School> findById(Long schoolId);
}
