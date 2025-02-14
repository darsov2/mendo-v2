package mk.ukim.finki.mendo.service.impl;

import mk.ukim.finki.mendo.model.Competition;
import mk.ukim.finki.mendo.model.CompetitionCycle;
import mk.ukim.finki.mendo.model.MendoUser;
import mk.ukim.finki.mendo.model.Participation;
import mk.ukim.finki.mendo.repository.ParticipationRepository;
import mk.ukim.finki.mendo.service.CompetitionCycleService;
import mk.ukim.finki.mendo.service.CompetitionService;
import mk.ukim.finki.mendo.service.MendoUserService;
import mk.ukim.finki.mendo.service.ParticipationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipationServiceImpl implements ParticipationService {
    private final ParticipationRepository participationRepository;
    private final MendoUserService mendoUserService;
    private final CompetitionCycleService competitionCycleService;
    private final CompetitionService competitionService;

    public ParticipationServiceImpl(ParticipationRepository participationRepository, MendoUserService mendoUserService, CompetitionCycleService competitionCycleService, CompetitionService competitionService) {
        this.participationRepository = participationRepository;
        this.mendoUserService = mendoUserService;
        this.competitionCycleService = competitionCycleService;
        this.competitionService = competitionService;
    }

    @Override
    public Participation save(Long userId, Long competitionId) {
        MendoUser mendoUser = mendoUserService.findById(userId);
        Competition competition = competitionService.findById(competitionId);
        return participationRepository.save(new Participation(mendoUser,competition,null, null));
    }

    @Override
    public List<Participation> findParticipationByCompetitionId(Long competitionId) {
        return participationRepository.findAllByCompetition_Id(competitionId);
    }

    @Override
    public List<Participation> saveAll(List<Participation> participations) {
        return participationRepository.saveAll(participations);
    }
}
