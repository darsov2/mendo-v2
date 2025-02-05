package mk.ukim.finki.mendo.service;

import mk.ukim.finki.mendo.model.CompetitionTask;

public interface CompetitionTaskService {
    CompetitionTask save(CompetitionTask task);
    CompetitionTask findById(long id);
}
