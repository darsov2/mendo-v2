package mk.ukim.finki.mendo.service.impl;

import mk.ukim.finki.mendo.model.Competition;
import mk.ukim.finki.mendo.model.CompetitionCycle;
import mk.ukim.finki.mendo.model.enums.CompetitionTypes;
import mk.ukim.finki.mendo.repository.CompetitionCycleRepository;
import mk.ukim.finki.mendo.repository.CompetitionRepository;
import mk.ukim.finki.mendo.service.CompetitionCycleService;
import mk.ukim.finki.mendo.service.CompetitionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionCycleService competitionCycleService;
    private final CompetitionRepository competitionRepository;

    public CompetitionServiceImpl(CompetitionCycleService service, CompetitionRepository competitionRepository) {
        this.competitionCycleService = service;
        this.competitionRepository = competitionRepository;
    }

    @Override
    public Competition addCompetition(String title, LocalDate startDate, LocalDateTime startTime, LocalDateTime endTime, CompetitionTypes type, String place, String info, LocalDateTime deadline, Long cycleId) {
        CompetitionCycle cycle = competitionCycleService.findById(cycleId);
        return competitionRepository.save(new Competition(title, startDate, startTime, endTime, type, place, info, deadline, cycle));
    }
}
