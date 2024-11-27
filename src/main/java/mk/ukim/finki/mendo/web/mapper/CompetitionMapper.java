package mk.ukim.finki.mendo.web.mapper;

import mk.ukim.finki.mendo.model.Competition;
import mk.ukim.finki.mendo.web.request.CompetitionRequest;
import mk.ukim.finki.mendo.service.CompetitionService;
import org.springframework.stereotype.Service;

@Service
public class CompetitionMapper {

    private final CompetitionService competitionService;


    public CompetitionMapper(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    public Competition addCompetition(CompetitionRequest request) {
        System.out.println(request.getCycleId());
        return competitionService.addCompetition(
                request.getTitle(),
                request.getStartDate(),
                request.getStartDateTime(),
                request.getEndDateTime(),
                request.getType(),
                request.getPlace(),
                request.getInfo(),
                request.getDeadline(),
                request.getCycleId()
        );

    }
}
