package mk.ukim.finki.mendo.web.mapper;

import java.util.List;

import mk.ukim.finki.mendo.model.MendoUser;
import mk.ukim.finki.mendo.model.dto.OptionDTO;
import mk.ukim.finki.mendo.service.*;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;

@Service
public class UtilsMapper {

  private final ActivityTagService activityTagService;
  private final CategoryService categoryService;
  private final TopicService topicService;
  private final RoomsService roomsService;
  private final TaskService taskService;
  private final CompetitionCycleService competitionCycleService;
  private final MendoUserService mendoUserService;

  public UtilsMapper(ActivityTagService activityTagService, CategoryService categoryService,
                     TopicService topicService, RoomsService roomsService, TaskService taskService, CompetitionCycleService competitionCycleService, MendoUserService mendoUserService) {
    this.activityTagService = activityTagService;
    this.categoryService = categoryService;
    this.topicService = topicService;
      this.roomsService = roomsService;
      this.taskService = taskService;
    this.competitionCycleService = competitionCycleService;
      this.mendoUserService = mendoUserService;
  }

  public List<OptionDTO<Long>> getAllActivityTagsAsOptions() {
    return activityTagService.findAll().stream()
        .map(x -> new OptionDTO<>(x.getId(), x.getName())).toList();
  }

  public List<OptionDTO<Long>> getAllCategoriesAsOptions() {
    return categoryService.findAll().stream().map(x -> new OptionDTO<>(x.getId(), x.getName()))
        .toList();
  }

  public List<OptionDTO<Long>> getAllTopicsAsOptions() {
    return topicService.findAll().stream().map(x -> new OptionDTO<>(x.getId(), x.getTitle()))
        .toList();
  }

  public List<OptionDTO<Long>> getAllRoomsAsOptions() {
    return roomsService.findAll().stream().map(x -> new OptionDTO<>(x.getId(), x.getCity() + " " + x.getName()))
            .toList();
  }

  public List<OptionDTO<Long>> getAllModeratorUsersAsOptions() {
    return mendoUserService.findAllByRoleModerator().stream().map(x -> new OptionDTO<>(x.getId(), "@" +  x.getUsername() + " - " + x.getName() + " " + x.getSurname()))
            .toList();
  }


  public List<OptionDTO<Long>> getAllUpcomingCyclesAsOptions() {
    return competitionCycleService.findAllUpcoming().stream().map(x -> new OptionDTO<>(x.getId(), x.getName()))
            .toList();
  }

  public List<OptionDTO<Long>> getAllTasksAsOptions() {
    return taskService.getAllTasks().stream().map(x -> new OptionDTO<>(x.getId(), x.getTitle()))
            .toList();
  }
}
