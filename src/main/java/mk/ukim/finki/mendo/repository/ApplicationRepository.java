package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
