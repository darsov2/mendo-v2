package mk.ukim.finki.mendo.service;

import mk.ukim.finki.mendo.model.Category;
import mk.ukim.finki.mendo.model.Lecture;
import mk.ukim.finki.mendo.model.Task;

import java.util.List;

public interface ActivitesService {
    List<Lecture> findAllLectures();
    List<Task> findAllTasks();
    List<Lecture> findAllLecturesByCategory(Category category);
    List<Task> findAllTasksByCategory(Category category);
}
