package mk.ukim.finki.mendo.service;

import mk.ukim.finki.mendo.model.Participation;

import java.util.List;

public interface ParticipationService {
    Participation save(Long userId, Long cycleId);
    List<Participation> findParticipationByCompetitionId(Long competitionId);

    List<Participation> saveAll(List<Participation> participations);
}
