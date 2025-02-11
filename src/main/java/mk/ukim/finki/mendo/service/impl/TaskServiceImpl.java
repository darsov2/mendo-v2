package mk.ukim.finki.mendo.service.impl;

import mk.ukim.finki.mendo.model.Task;
import mk.ukim.finki.mendo.repository.TaskRepository;
import mk.ukim.finki.mendo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }
}
