package mk.ukim.finki.mendo.service.impl;

import mk.ukim.finki.mendo.model.CompetitionTask;
import mk.ukim.finki.mendo.repository.CompetitionTaskRepository;
import mk.ukim.finki.mendo.service.CompetitionTaskService;
import org.springframework.stereotype.Service;

@Service
public class CompetitionTaskImpl implements CompetitionTaskService {
    private final CompetitionTaskRepository competitionTaskRepository;

    public CompetitionTaskImpl(CompetitionTaskRepository competitionTaskRepository) {
        this.competitionTaskRepository = competitionTaskRepository;
    }

    @Override
    public CompetitionTask save(CompetitionTask task) {
        return competitionTaskRepository.save(task);
    }

    @Override
    public CompetitionTask findById(long id) {
        return competitionTaskRepository.findById(id).orElse(null);
    }
}
