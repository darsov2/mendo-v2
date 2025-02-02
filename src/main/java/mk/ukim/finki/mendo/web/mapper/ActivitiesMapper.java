package mk.ukim.finki.mendo.web.mapper;

import mk.ukim.finki.mendo.model.ActivityTag;
import mk.ukim.finki.mendo.model.Category;
import mk.ukim.finki.mendo.model.Lecture;
import mk.ukim.finki.mendo.model.Task;
import mk.ukim.finki.mendo.model.Topic;
import mk.ukim.finki.mendo.model.dto.ActivityDTO;
import mk.ukim.finki.mendo.model.dto.LectureDTO;
import mk.ukim.finki.mendo.model.dto.LectureEditDTO;
import mk.ukim.finki.mendo.model.dto.TaskDTO;
import mk.ukim.finki.mendo.service.ActivityTagService;
import mk.ukim.finki.mendo.service.CategoryService;
import mk.ukim.finki.mendo.service.ContentService;
import mk.ukim.finki.mendo.service.TopicService;
import mk.ukim.finki.mendo.web.request.CreateLectureRequest;
import mk.ukim.finki.mendo.web.request.CreateTaskRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivitiesMapper {

  private final ContentService contentService;
  private final CategoryService categoryService;
  private final ActivityTagService activityTagService;
  private final TopicService topicService;

  public ActivitiesMapper(ContentService contentService, CategoryService categoryService,
      ActivityTagService activityTagService, TopicService topicService) {
    this.contentService = contentService;
    this.categoryService = categoryService;
    this.activityTagService = activityTagService;
    this.topicService = topicService;
  }

  public List<ActivityDTO> findAllByCategoryId(Long categoryId) {
    if (categoryId == null) {
      return contentService.findAll().stream().map(ActivityDTO::toDTO).toList();
    }
    return contentService.findAllByCategoryId(categoryId).stream().map(ActivityDTO::toDTO).toList();
  }

  public ActivityDTO saveLecture(CreateLectureRequest request, Long lectureId) {
    Category category = categoryService.findById(request.getCategoryId());
    List<ActivityTag> activityTags = request.getTagsId().stream().map(activityTagService::findById)
        .toList();
    Topic topic = null;
    if (request.getTopicId() != null) {
      topic = topicService.findById(request.getTopicId());
    } else if (request.getManualTopicInput() != null) {
      topic = topicService.create(new Topic(request.getManualTopicInput()));
    }

    if (lectureId != null) {
      return ActivityDTO.toDTO(
          contentService.updateLecture(lectureId, category, activityTags, request.getTitle(),
              request.getSource(), request.getText(), topic));
    }
    return ActivityDTO.toDTO(
        contentService.createLecture(category, activityTags, request.getTitle(),
            request.getSource(), request.getText(), topic));
  }


  public ActivityDTO saveTask(CreateTaskRequest request, Long lectureId) {
    Category category = categoryService.findById(request.getCategoryId());
    List<ActivityTag> activityTags = request.getTagsId().stream().map(activityTagService::findById)
        .toList();
    Topic topic = null;
    if (request.getTopicId() != null) {
      topic = topicService.findById(request.getTopicId());
    } else if (request.getManualTopicInput() != null) {
      topic = topicService.create(new Topic(request.getManualTopicInput()));
    }

    if (lectureId != null) {
      return ActivityDTO.toDTO(
          contentService.updateTask(lectureId, category, activityTags, request.getTitle(),
              request.getSource(), request.getText(), request.getInputText(),
              request.getOutputText(), topic));
    }
    return ActivityDTO.toDTO(
        contentService.createTask(category, activityTags, request.getTitle(),
            request.getSource(), request.getText(), request.getInputText(), request.getOutputText(),
            topic));
  }

  public LectureDTO getLecturePreview(Long lectureId) {
    Lecture lecture = contentService.findLectureById(lectureId);
    return LectureDTO.toDTO(contentService.findLectureById(lectureId));
  }

  public TaskDTO getTaskPreview(Long lectureId) {
    Task task = contentService.findTaskById(lectureId);
    return TaskDTO.toDTO(task);
  }

  public LectureEditDTO getLectureEditDto(Long lectureId) {
    return LectureEditDTO.toDTO(contentService.findLectureById(lectureId));
  }
}
