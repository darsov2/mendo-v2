package mk.ukim.finki.mendo.service.impl;

import mk.ukim.finki.mendo.model.CompetitionCycle;
import mk.ukim.finki.mendo.repository.CompetitionCycleRepository;
import mk.ukim.finki.mendo.service.CompetitionCycleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public List<CompetitionCycle> findAllSortedByYearDesc() {
        return competitionCycleRepository.findAllByOrderByYearDesc();
    }

    @Override
    public CompetitionCycle create(String name, LocalDate year, LocalDateTime registrationDeadline) {
        if (competitionCycleRepository.existsByNameAndYear(name, year)) {
            throw new IllegalArgumentException("Competition cycle with this name and year already exists");
        }

        CompetitionCycle cycle = new CompetitionCycle(name, year, registrationDeadline);
        return competitionCycleRepository.save(cycle);
    }

    @Override
    public void deleteById(Long id) {
        competitionCycleRepository.deleteById(id);
    }

    @Override
    public Optional<CompetitionCycle> update(Long id, String name, LocalDate year, LocalDateTime registrationDeadline) {
        return competitionCycleRepository.findById(id)
                .map(cycle -> {
                    cycle.setName(name);
                    cycle.setYear(year);
                    cycle.setRegistrationDeadline(registrationDeadline);
                    return competitionCycleRepository.save(cycle);
                });
    }

    @Override
    public List<CompetitionCycle> findAll() {
        return competitionCycleRepository.findAll();
    }

    @Override
    public List<CompetitionCycle> findAllUpcoming() {
        return competitionCycleRepository.findAllByYearAfter(LocalDate.now().minusYears(1));
    }
}
