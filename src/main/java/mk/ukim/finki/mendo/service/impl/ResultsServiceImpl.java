package mk.ukim.finki.mendo.service.impl;

import mk.ukim.finki.mendo.model.*;
import mk.ukim.finki.mendo.model.dto.ResultDTO;
import mk.ukim.finki.mendo.repository.CompetitionRepository;
import mk.ukim.finki.mendo.repository.ParticipationRepository;
import mk.ukim.finki.mendo.repository.SubmissionRepository;
import mk.ukim.finki.mendo.service.ResultsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ResultsServiceImpl implements ResultsService {
    private final CompetitionRepository competitionRepository;
    private final ParticipationRepository participationRepository;
    private final SubmissionRepository submissionRepository;

    public ResultsServiceImpl(CompetitionRepository competitionRepository, ParticipationRepository participationRepository, SubmissionRepository submissionRepository) {
        this.competitionRepository = competitionRepository;
        this.participationRepository = participationRepository;
        this.submissionRepository = submissionRepository;
    }

    @Override
    public List<ResultDTO> getResultOfCompetition(Long competitionId) {
        Competition competition = competitionRepository.findById(competitionId).orElseThrow();
        List<Participation> participants = participationRepository.findAllByCompetition_Id(competitionId);

        List<ResultDTO> results = new ArrayList<>();

        for (Participation participation : participants) {
            List<Integer> points = new ArrayList<>();
            for (CompetitionTask task : competition.getTasks()){
                Optional<Submission> lastSubmission = submissionRepository
                        .findLastSubmissionByUserAndCompetitionTask(
                                participation.getMendoUser(),
                                task
                        );
                if (lastSubmission.isPresent()){
                    points.add(lastSubmission.get().getPoints());
                }
                else {
                    points.add(0);
                }
            }
            results.add(new ResultDTO(participation.getMendoUser(),points,points.stream().mapToInt(x->x).sum()));
        }
        return results.stream()
                .sorted((x, x2) -> x2.totalPoints.compareTo(x.totalPoints)).toList();

    }
}
