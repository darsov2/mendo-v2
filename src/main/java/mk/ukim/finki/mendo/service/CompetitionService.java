package mk.ukim.finki.mendo.service;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import mk.ukim.finki.mendo.model.Competition;
import mk.ukim.finki.mendo.model.CompetitionCycle;
import mk.ukim.finki.mendo.model.enums.CompetitionTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface CompetitionService {
    Competition addCompetition(String title,
                               LocalDate startDate,
                               LocalDateTime startTime,
                               LocalDateTime endTime,
                               CompetitionTypes type,
                               String place,
                               String info,
                               LocalDateTime deadline,
                               Long cycleId
    );

}