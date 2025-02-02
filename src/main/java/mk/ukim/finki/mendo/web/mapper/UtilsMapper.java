package mk.ukim.finki.mendo.web.mapper;

import java.util.List;
import mk.ukim.finki.mendo.model.dto.OptionDTO;
import mk.ukim.finki.mendo.service.ActivityTagService;
import mk.ukim.finki.mendo.service.CategoryService;
import mk.ukim.finki.mendo.service.TopicService;
import org.springframework.stereotype.Service;

@Service
public class UtilsMapper {

  private final ActivityTagService activityTagService;
  private final CategoryService categoryService;
  private final TopicService topicService;

  public UtilsMapper(ActivityTagService activityTagService, CategoryService categoryService,
      TopicService topicService) {
    this.activityTagService = activityTagService;
    this.categoryService = categoryService;
    this.topicService = topicService;
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
}
