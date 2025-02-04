package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.CompetitionTask;
import mk.ukim.finki.mendo.model.MendoUser;
import mk.ukim.finki.mendo.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    @Query("SELECT s FROM Submission s " +
            "WHERE s.mendoUser = :user " +
            "AND s.competitionTask = :competitionTask " +
            "ORDER BY s.dateCreated DESC " +
            "LIMIT 1")
    Optional<Submission> findLastSubmissionByUserAndCompetitionTask(
            @Param("user") MendoUser user,
            @Param("competitionTask") CompetitionTask competitionTask
    );
}
