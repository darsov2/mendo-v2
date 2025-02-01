package mk.ukim.finki.mendo.service.impl;

import mk.ukim.finki.mendo.model.Competition;
import mk.ukim.finki.mendo.model.CompetitionCycle;
import mk.ukim.finki.mendo.model.MendoUser;
import mk.ukim.finki.mendo.model.Participation;
import mk.ukim.finki.mendo.repository.ParticipationRepository;
import mk.ukim.finki.mendo.service.CompetitionCycleService;
import mk.ukim.finki.mendo.service.MendoUserService;
import mk.ukim.finki.mendo.service.ParticipationService;
import org.springframework.stereotype.Service;

@Service
public class ParticipationServiceImpl implements ParticipationService {
    private final ParticipationRepository participationRepository;
    private final MendoUserService mendoUserService;
    private final CompetitionCycleService competitionCycleService;

    public ParticipationServiceImpl(ParticipationRepository participationRepository, MendoUserService mendoUserService, CompetitionCycleService competitionCycleService) {
        this.participationRepository = participationRepository;
        this.mendoUserService = mendoUserService;
        this.competitionCycleService = competitionCycleService;
    }

    @Override
    public Participation save(Long userId, Long cycleId) {
        MendoUser mendoUser = mendoUserService.findById(userId);
        Competition competition = competitionCycleService.findById(cycleId).getCompetitions().get(0);
        return participationRepository.save(new Participation(mendoUser,competition,null, null));
    }
}
