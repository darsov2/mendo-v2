package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findByCompetition_Cycle_IdAndStudent_Id(Long cycleId, Long userId);
    List<Application> findByStudent_StudiesSchool_Id(Long schoolId);
    List<Application> findByStudent_StudiesSchool_IdIn(List<Long> schoolIds);
    List<Application> findByStudent_StudiesSchool_IdInAndConfirmedFalse(List<Long> schoolIds);
    Application findByStudent_UsernameAndCompetition_Id(String username, Long competitionId);
    @Query("SELECT a FROM Application a WHERE a.mentor.id = :mentorId AND a.student.studiesSchool IN (SELECT ts FROM a.mentor.teachesSchools ts)")
    List<Application> findApplicationsWhereStudentsStudyAtMentorTeachingSchools(@Param("mentorId") Long mentorId);
}
