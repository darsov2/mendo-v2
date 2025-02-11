package mk.ukim.finki.mendo.service;

import mk.ukim.finki.mendo.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(Long id);
}
