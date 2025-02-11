package mk.ukim.finki.mendo.web.mapper;

import mk.ukim.finki.mendo.model.Competition;
import mk.ukim.finki.mendo.model.Participation;
import mk.ukim.finki.mendo.model.Rooms;
import mk.ukim.finki.mendo.service.ParticipationService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ParticipationMapper {

    private final ParticipationService participationService;
    private final RoomMapper roomMapper;

    public ParticipationMapper(ParticipationService participationService, RoomMapper roomMapper) {
        this.participationService = participationService;
        this.roomMapper = roomMapper;
    }

    public List<Participation> getParticipantsForCompetition(Long competitionId){
        return participationService.findParticipationByCompetitionId(competitionId);
    }

    public void changeRoomForParticipation(Long id, Map<String, String> allParams) {
        List<Participation> participations = getParticipantsForCompetition(id);

        List<Long> roomIds = participations.stream()
                .map(p -> Long.parseLong(allParams.get(p.getId().toString())))
                .toList();

        List<Rooms> rooms = roomMapper.findAllRoomsByIds(roomIds);


        participations.forEach(p -> {
            p.setRoom(rooms.stream()
                    .filter(r -> r.getId().toString().equals(allParams.get(p.getId().toString())))
                    .findFirst().orElse(null));
        });

        participationService.saveAll(participations);

    }
}
