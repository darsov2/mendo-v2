package mk.ukim.finki.mendo.service.impl;

import mk.ukim.finki.mendo.model.Category;
import mk.ukim.finki.mendo.model.Lecture;
import mk.ukim.finki.mendo.model.Task;
import mk.ukim.finki.mendo.repository.LectureRepository;
import mk.ukim.finki.mendo.repository.TaskRepository;
import mk.ukim.finki.mendo.service.ActivitesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivitiesServiceImpl implements ActivitesService {
    private final LectureRepository lectureRepository;
    private final TaskRepository taskRepository;

    public ActivitiesServiceImpl(LectureRepository lectureRepository, TaskRepository taskRepository) {
        this.lectureRepository = lectureRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Lecture> findAllLectures() {
        return lectureRepository.findAll();
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Lecture> findAllLecturesByCategory(Category category) {
        return lectureRepository.findAllByCategory(category);
    }

    @Override
    public List<Task> findAllTasksByCategory(Category category) {
        return taskRepository.findAllByCategory(category);
    }
}
