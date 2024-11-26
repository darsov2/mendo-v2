package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.Category;
import mk.ukim.finki.mendo.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findAllByCategory(Category category);
}
