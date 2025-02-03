package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.Quota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuotaRepository extends JpaRepository<Quota, Long> {

    List<Quota> findAllByCompetition_Id(Long competitionId);
    List<Quota> deleteAllByCompetition_Id(Long competitionId);


}
