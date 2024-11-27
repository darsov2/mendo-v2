package mk.ukim.finki.mendo.service;

import mk.ukim.finki.mendo.model.Participation;

public interface ParticipationService {
    Participation save(Long userId, Long cycleId);
}
