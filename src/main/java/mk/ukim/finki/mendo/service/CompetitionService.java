package mk.ukim.finki.mendo.service;

import mk.ukim.finki.mendo.model.Competition;
import mk.ukim.finki.mendo.model.Participation;
import mk.ukim.finki.mendo.model.enums.CompetitionTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CompetitionService {
    Competition addCompetition(String title,
                               LocalDate startDate,
                               LocalDateTime startTime,
                               LocalDateTime endTime,
                               CompetitionTypes type,
                               String place,
                               String info,
                               LocalDateTime deadline,
                               Long cycleId,
                               Long parentId,
                               List<Long> roomIds,
                               List<Long> taskIds,
                               List<Long> taskPoints,
                               Boolean requiresRegistration, Boolean visibleToPublic, Boolean canStudentRegister, LocalDateTime registrationOpens, LocalDateTime registrationCloses,
                               List<Long> moderators);

    List<Competition> findAllWithoutCycle();
    List<Participation> distributeStudentsForCompetition(Long id);
    List<Competition> findAll();
    List<Competition> findAllByCycleId(Long cycleId);
//    Competition create(String title, LocalDate startDate, LocalDateTime startTime,
//                       LocalDateTime endTime, CompetitionTypes type, String place,
//                       String info, LocalDateTime deadline, Long cycleId, List<Long> roomIds);
    Competition findById(Long id);
    Optional<Competition> update(Long id, String title, LocalDate startDate,
                                 LocalDateTime startTime, LocalDateTime endTime,
                                 CompetitionTypes type, String place, String info,
                                 LocalDateTime deadline, Long cycleId,Long parentId, List<Long> roomIds,
                                 List<Long> taskIds, List<Long> taskPoints);
    void deleteById(Long id);
    List<Competition> findAllOpenRegistrationCompetitions();

}
