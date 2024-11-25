package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
