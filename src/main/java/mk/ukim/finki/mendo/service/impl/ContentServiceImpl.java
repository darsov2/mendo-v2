package mk.ukim.finki.mendo.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import mk.ukim.finki.mendo.model.ActivityTag;
import mk.ukim.finki.mendo.model.Category;
import mk.ukim.finki.mendo.model.Content;
import mk.ukim.finki.mendo.model.Lecture;
import mk.ukim.finki.mendo.model.Task;
import mk.ukim.finki.mendo.model.Topic;
import mk.ukim.finki.mendo.model.enums.ContentType;
import mk.ukim.finki.mendo.repository.ContentRepository;
import mk.ukim.finki.mendo.repository.LectureRepository;
import mk.ukim.finki.mendo.repository.TaskRepository;
import mk.ukim.finki.mendo.service.CategoryService;
import mk.ukim.finki.mendo.service.ContentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

  private final ContentRepository contentRepository;
  private final CategoryService categoryService;
  private final LectureRepository lectureRepository;
  private final TaskRepository taskRepository;

  public ContentServiceImpl(ContentRepository contentRepository, CategoryService categoryService,
      LectureRepository lectureRepository, TaskRepository taskRepository) {
    this.contentRepository = contentRepository;
    this.categoryService = categoryService;
    this.lectureRepository = lectureRepository;
    this.taskRepository = taskRepository;
  }

  @Override
  public List<Content> findAll() {
    return contentRepository.findAll();
  }

  @Override
  public List<Content> findAllByCategoryId(Long categoryId) {
    Category category = categoryService.findById(categoryId);
    return contentRepository.findAllByCategory(category);
  }

  @Override
  public Lecture createLecture(Category category, List<ActivityTag> activityTags, String title,
      String source, String content, Topic topic) {
    Lecture lecture = new Lecture();
    lecture.setCategory(category);
    lecture.setTitle(title);
    lecture.setSource(source);
    lecture.setTags(activityTags);
    lecture.setOrder(0);
    lecture.setType(ContentType.LECTURE);
    lecture.setText(content);
    lecture.setTopic(topic);
    return lectureRepository.save(lecture);
  }

  @Override
  public Lecture updateLecture(Long lectureId, Category category, List<ActivityTag> activityTags,
      String title, String source, String content, Topic topic) {
    Lecture lecture = this.findLectureById(lectureId);

    lecture.setCategory(category);
    lecture.setTitle(title);
    lecture.setSource(source);
    lecture.setTags(new ArrayList<>(activityTags));
    lecture.setText(content);
    lecture.setTopic(topic);
    return lectureRepository.save(lecture);
  }

  @Override
  public Task createTask(Category category, List<ActivityTag> activityTags, String title,
      String source, String content, String inputContent, String outputContent, Topic topic) {
    Task task = new Task();
    task.setCategory(category);
    task.setTitle(title);
    task.setSource(source);
    task.setTags(activityTags);
    task.setOrder(0);
    task.setType(ContentType.LECTURE);
    task.setText(content);
    task.setInputFormat(inputContent);
    task.setOutputFormat(outputContent);
    task.setTopic(topic);
    return taskRepository.save(task);
  }

  @Override
  public Task updateTask(Long taskId, Category category, List<ActivityTag> activityTags,
      String title, String source, String content, String inputContent, String outputContent,
      Topic topic) {
    Task task = findTaskById(taskId);

    task.setCategory(category);
    task.setTitle(title);
    task.setSource(source);
    task.setTags(activityTags);
    task.setOrder(0);
    task.setType(ContentType.LECTURE);
    task.setText(content);
    task.setInputFormat(inputContent);
    task.setOutputFormat(outputContent);
    task.setTopic(topic);
    return taskRepository.save(task);
  }


  @Override
  public Lecture findLectureById(Long id) {
    return lectureRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  @Override
  public Task findTaskById(Long id) {
    return taskRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }
}
