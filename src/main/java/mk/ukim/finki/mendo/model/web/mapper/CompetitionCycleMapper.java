package mk.ukim.finki.mendo.model.web.mapper;

import mk.ukim.finki.mendo.model.CompetitionCycle;
import mk.ukim.finki.mendo.model.web.request.CompetitionCycleRequest;
import mk.ukim.finki.mendo.service.CompetitionCycleService;
import org.springframework.stereotype.Service;

@Service
public class CompetitionCycleMapper {
    private final CompetitionCycleService competitionCycleService;

    public CompetitionCycleMapper(CompetitionCycleService competitionCycleService) {
        this.competitionCycleService = competitionCycleService;
    }

    public CompetitionCycle addCompetitionCycle(CompetitionCycleRequest request) {
        return competitionCycleService.addCycle(request.getName(), request.getYear(), request.getRegistrationDeadline());
    }


}
