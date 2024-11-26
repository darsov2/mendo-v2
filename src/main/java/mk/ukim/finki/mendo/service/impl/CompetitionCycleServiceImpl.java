package mk.ukim.finki.mendo.service.impl;

import mk.ukim.finki.mendo.model.CompetitionCycle;
import mk.ukim.finki.mendo.repository.CompetitionCycleRepository;
import mk.ukim.finki.mendo.service.CompetitionCycleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompetitionCycleServiceImpl implements CompetitionCycleService {

    private final CompetitionCycleRepository competitionCycleRepository;

    public CompetitionCycleServiceImpl(CompetitionCycleRepository competitionCycleRepository) {
        this.competitionCycleRepository = competitionCycleRepository;
    }

    @Override
    public CompetitionCycle addCycle(String name, LocalDate year, LocalDateTime registrationDeadline) {
        return competitionCycleRepository.save(new CompetitionCycle(name, year, registrationDeadline));
    }

    @Override
    public CompetitionCycle findById(Long id) {
        return competitionCycleRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<CompetitionCycle> findAll() {
        return competitionCycleRepository.findAll();
    }
}
