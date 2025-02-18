package mk.ukim.finki.mendo.web.mapper;

import mk.ukim.finki.mendo.model.BaseEntity;
import mk.ukim.finki.mendo.model.Competition;
import mk.ukim.finki.mendo.model.Participation;
import mk.ukim.finki.mendo.model.dto.CycleOrCompetitionDTO;
import mk.ukim.finki.mendo.model.dto.ResultDTO;
import mk.ukim.finki.mendo.web.request.CompetitionRequest;
import mk.ukim.finki.mendo.service.CompetitionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CompetitionMapper {

    private final CompetitionService competitionService;


    public CompetitionMapper(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    public Competition addCompetition(CompetitionRequest request) {
        return competitionService.addCompetition(
                request.getTitle(),
                request.getStartDate(),
                request.getStartDateTime(),
                request.getEndDateTime(),
                request.getType(),
                request.getPlace(),
                request.getInfo(),
                request.getDeadline(),
                request.getCycleId(),
                request.getParentId(),
                request.getRoomIds(),
                request.getTaskIds(),
                request.getTaskPoints()
        );
    }

    public Optional<Competition> editCompetition(Long id, CompetitionRequest request) {
        return competitionService.update(
                id,
                request.getTitle(),
                request.getStartDate(),
                request.getStartDateTime(),
                request.getEndDateTime(),
                request.getType(),
                request.getPlace(),
                request.getInfo(),
                request.getDeadline(),
                request.getCycleId(),
                request.getParentId(),
                request.getRoomIds(),
                request.getTaskIds(),
                request.getTaskPoints()
        );
    }

    public Competition findById(Long id){
        return competitionService.findById(id);
    }



    public List<CycleOrCompetitionDTO> getCyclesOrCompetitions() {
        List<CycleOrCompetitionDTO> cyclesOrCompetitions = new ArrayList<>();
        List<Competition> competitions = competitionService.findAll().stream().sorted(Comparator.comparing(Competition::getStartDate)).toList();
        competitions.forEach(competition -> {
            if (competition.getCycle() != null){
                cyclesOrCompetitions.add(new CycleOrCompetitionDTO(competition.getCycle(), null));
            } else {
                cyclesOrCompetitions.add(new CycleOrCompetitionDTO(null, competition));
            }
        });
        return cyclesOrCompetitions.stream().distinct().toList();
    }



    public List<Competition> listCompetitions(){
        return  competitionService.findAll();
    }

    public CompetitionRequest toCompetitionRequest(Competition competition) {
        CompetitionRequest request = new CompetitionRequest();

        request.setTitle(competition.getTitle());
        request.setStartDate(competition.getStartDate());
        // Convert LocalDateTime to String time format
        request.setStartTime(competition.getStartTime().toLocalTime().toString());
        request.setEndTime(competition.getEndTime().toLocalTime().toString());
        request.setType(competition.getType());
        request.setPlace(competition.getPlace());
        request.setInfo(competition.getInfo());
        request.setDeadline(competition.getDeadline());

        // Handle potential null values for relationships
        if (competition.getCycle() != null) {
            request.setCycleId(competition.getCycle().getId());
        }

        if (competition.getParentCompetition() != null) {
            request.setParentId(competition.getParentCompetition().getId());
        }

        // Convert rooms to room IDs
        if (competition.getRooms() != null) {
            request.setRoomIds(competition.getRooms().stream()
                    .map(BaseEntity::getId)
                    .toList());
        }

        // Convert competition tasks to task IDs and points
        if (competition.getTasks() != null) {
            request.setTaskIds(competition.getTasks().stream()
                    .map(task -> task.getTask().getId())
                    .toList());

            request.setTaskPoints(competition.getTasks().stream()
                    .map(task -> (long)task.getPoints())
                    .toList());
        }

        return request;
    }

}
