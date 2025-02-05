package mk.ukim.finki.mendo.service.impl;

import mk.ukim.finki.mendo.model.*;
import mk.ukim.finki.mendo.model.enums.CompetitionTypes;
import mk.ukim.finki.mendo.repository.CompetitionRepository;
import mk.ukim.finki.mendo.repository.ParticipationRepository;
import mk.ukim.finki.mendo.repository.RoomsRepository;
import mk.ukim.finki.mendo.service.CompetitionCycleService;
import mk.ukim.finki.mendo.service.CompetitionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionCycleService competitionCycleService;
    private final CompetitionRepository competitionRepository;
    private final ParticipationRepository participationRepository;
    private final RoomsRepository roomsRepository;


    public CompetitionServiceImpl(CompetitionCycleService service, CompetitionRepository competitionRepository, ParticipationRepository participationRepository, RoomsRepository roomsRepository) {
        this.competitionCycleService = service;
        this.competitionRepository = competitionRepository;
        this.participationRepository = participationRepository;
        this.roomsRepository = roomsRepository;
    }

    @Override
    public Competition addCompetition(String title, LocalDate startDate, LocalDateTime startTime, LocalDateTime endTime, CompetitionTypes type, String place, String info, LocalDateTime deadline, Long cycleId, Long parentId, List<Long> roomIds) {

        List<Rooms> rooms = roomsRepository.findAllById(roomIds);

        if (cycleId == null && parentId == null) {
            return competitionRepository.save(new Competition(title, startDate, startTime, endTime, type, place, info, deadline, rooms));
        }
        else if (cycleId == null) {
            Competition parent = findById(parentId);
            return competitionRepository.save(new Competition(title, startDate, startTime, endTime, type, place, info, deadline, parent, rooms));
        }
        else if (parentId == null) {
            CompetitionCycle cycle = competitionCycleService.findById(cycleId);
            return competitionRepository.save(new Competition(title, startDate, startTime, endTime, type, place, info, deadline, cycle, rooms));
        }
        CompetitionCycle cycle = competitionCycleService.findById(cycleId);
        Competition parent = findById(parentId);
        return competitionRepository.save(new Competition(title, startDate, startTime, endTime, type, place, info, deadline, cycle, parent, rooms));
    }

    @Override
    public List<Competition> findAllWithoutCycle() {
        return competitionRepository.findAllByCycleIsNullOrderByStartTimeAsc();
    }

    @Override
    public List<Participation> distributeStudentsForCompetition(Long id) {
        List<Participation> participations = participationRepository.findAllByCompetition_Id(id);

        Collections.shuffle(participations);

        Competition competition = findById(id);

        if (competition.getRooms().stream().mapToInt(Rooms::getCapacity).sum() < participations.size()) {
            throw new RuntimeException();
        }

        competition.getRooms().forEach(room -> {
            participations.stream().filter(p -> p.getRoom() == null).limit(room.getCapacity()).forEach(p -> p.setRoom(room));
        });
        return participationRepository.saveAll(participations);
    }



    @Override
    public List<Competition> findAll() {
        return competitionRepository.findAll();
    }

    @Override
    public List<Competition> findAllByCycleId(Long cycleId) {
        return competitionRepository.findAllByCycleIdOrderByStartTimeAsc(cycleId);
    }

    @Override
    public Competition create(String title, LocalDate startDate, LocalDateTime startTime, LocalDateTime endTime, CompetitionTypes type, String place, String info, LocalDateTime deadline, Long cycleId, List<Long> rooms) {
        CompetitionCycle cycle = competitionCycleService.findById(cycleId);

        if (cycle == null) {
            throw new IllegalArgumentException("Competition cycle not found");
        }
//        List<Rooms> roomsList = rooms.stream().map(r -> roomsRepository.findById(r).orElseThrow(RuntimeException::new)).collect(Collectors.toList());
        Competition competition = new Competition(title, startDate, startTime, endTime,
                type, place, info, deadline, cycle, roomsRepository.findAllById(rooms));

        return competitionRepository.save(competition);
    }

    @Override
    public Competition findById(Long id) {
        return competitionRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Optional<Competition> update(Long id, String title, LocalDate startDate, LocalDateTime startTime, LocalDateTime endTime, CompetitionTypes type, String place, String info, LocalDateTime deadline, Long cycleId, List<Long> rooms) {
        List<Rooms> roomsList = rooms.stream().map(r -> roomsRepository.findById(r).orElseThrow(RuntimeException::new)).collect(Collectors.toList());
        return competitionRepository.findById(id)

                .map(competition -> {
                    CompetitionCycle cycle = competitionCycleService.findById(cycleId);

                    competition.setTitle(title);
                    competition.setStartDate(startDate);
                    competition.setStartTime(startTime);
                    competition.setEndTime(endTime);
                    competition.setType(type);
                    competition.setPlace(place);
                    competition.setInfo(info);
                    competition.setDeadline(deadline);
                    competition.setCycle(cycle);
//                    competition.setRooms(roomsList);

                    return competitionRepository.save(competition);
                });
    }

    @Override
    public void deleteById(Long id) {
        competitionCycleService.deleteById(id);
    }

    @Override
    public List<Competition> findAllOpenRegistrationCompetitions() {
        return competitionRepository.findAllByRegistrationDateBetween(LocalDateTime.now());
    }
}
