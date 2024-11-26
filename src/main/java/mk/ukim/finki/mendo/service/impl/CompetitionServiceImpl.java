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
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Competition> findAll() {
        return competitionRepository.findAll();
    }

    @Override
    public List<Competition> findAllByCycleId(Long cycleId) {
        return competitionRepository.findAllByCycleIdOrderByStartTimeAsc(cycleId);
    }

    @Override
    public Competition create(String title, LocalDate startDate, LocalDateTime startTime, LocalDateTime endTime, CompetitionTypes type, String place, String info, LocalDateTime deadline, Long cycleId) {
        CompetitionCycle cycle = competitionCycleService.findById(cycleId);

        if (cycle == null) {
            throw new IllegalArgumentException("Competition cycle not found");
        }

        Competition competition = new Competition(title, startDate, startTime, endTime,
                type, place, info, deadline, cycle);

        return competitionRepository.save(competition);
    }

    @Override
    public Optional<Competition> findById(Long id) {
        return competitionRepository.findById(id);
    }

    @Override
    public Optional<Competition> update(Long id, String title, LocalDate startDate, LocalDateTime startTime, LocalDateTime endTime, CompetitionTypes type, String place, String info, LocalDateTime deadline, Long cycleId) {
        return competitionRepository.findById(id)
                .map(competition -> {
                    CompetitionCycle cycle = competitionCycleService.findById(cycleId);

                    competition.setTitle(title);
                    competition.setStartDate(startDate);
                    competition.setStartTime(startTime);
                    competition.setEndTime(endTime);
                    competition.setType(type);
                    competition.setPlace(place);
                    competition.setInfo(info);
                    competition.setDeadline(deadline);
                    competition.setCycle(cycle);

                    return competitionRepository.save(competition);
                });
    }

    @Override
    public void deleteById(Long id) {
        competitionCycleService.deleteById(id);
    }
}
