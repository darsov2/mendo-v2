package mk.ukim.finki.mendo.model.dto;

import lombok.Data;
import mk.ukim.finki.mendo.model.Competition;
import mk.ukim.finki.mendo.model.CompetitionCycle;

@Data
public class CycleOrCompetitionDTO {
    CompetitionCycle competitionCycle;
    Competition competition;

    public CycleOrCompetitionDTO(CompetitionCycle competitionCycle, Competition competition) {
        this.competitionCycle = competitionCycle;
        this.competition = competition;
    }
}
