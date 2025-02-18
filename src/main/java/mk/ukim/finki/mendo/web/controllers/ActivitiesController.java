package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.model.dto.ActivityDTO;
import mk.ukim.finki.mendo.model.dto.CategoryDTO;
import mk.ukim.finki.mendo.model.dto.LectureEditDTO;
import mk.ukim.finki.mendo.service.AuthorizationService;
import mk.ukim.finki.mendo.model.dto.TaskDTO;
import mk.ukim.finki.mendo.model.dto.TestGroupDTO;
import mk.ukim.finki.mendo.service.PostService;
import mk.ukim.finki.mendo.web.mapper.ActivitiesMapper;
import mk.ukim.finki.mendo.web.mapper.CategoryMapper;
import mk.ukim.finki.mendo.web.mapper.UtilsMapper;
import mk.ukim.finki.mendo.web.request.CreateLectureRequest;
import mk.ukim.finki.mendo.web.request.CreateTaskRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/activities")
public class ActivitiesController {

  private final CategoryMapper categoryMapper;
  private final ActivitiesMapper activitiesMapper;
  private final UtilsMapper utilsMapper;
  private final PostService postService;

  public ActivitiesController(CategoryMapper categoryMapper, ActivitiesMapper activitiesMapper,
                              UtilsMapper utilsMapper, AuthorizationService authorizationService) {
      UtilsMapper utilsMapper, PostService postService) {
    this.categoryMapper = categoryMapper;
    this.activitiesMapper = activitiesMapper;
    this.utilsMapper = utilsMapper;
    this.postService = postService;
      this.authorizationService = authorizationService;
  }

  @GetMapping
  public String activities(Model model, @RequestParam(required = false) Long categoryId) {
    List<CategoryDTO> categories = categoryMapper.getAllCategories(categoryId);
    List<ActivityDTO> activities = activitiesMapper.findAllByCategoryId(categoryId);
    model.addAttribute("categories", categories);
    model.addAttribute("activities", activities);
    model.addAttribute("bodyContent", "admin/activites");
    return "master";
  }

  @PostMapping("/lectures")
  public String addLecture(Model model, @ModelAttribute CreateLectureRequest request,
      RedirectAttributes redirectAttributes) {
    activitiesMapper.saveLecture(request, null);
    redirectAttributes.addAttribute("categoryId", request.getCategoryId());
    return "redirect:/activities";
  }

  @PostMapping("/tasks")
  public String addTask(Model model, @ModelAttribute CreateTaskRequest request,
      RedirectAttributes redirectAttributes) {
    activitiesMapper.saveTask(request, null);
    redirectAttributes.addAttribute("categoryId", request.getCategoryId());
    return "redirect:/activities";
  }

  @GetMapping("/tasks/{activityId}")
  public String previewTask(Model model, @PathVariable Long activityId) {
    TaskDTO task = activitiesMapper.getTaskPreview(activityId);
    model.addAttribute("task", task);
    model.addAttribute("posts", postService.findAllPostsByThreadId(task.getThread().getId()));
    model.addAttribute("bodyContent", "admin/task-preview");
    return "master";
  }

  @PostMapping("/tasks/{taskId}/cases/update-cases")
  public String updateCases(Model model, @PathVariable Long taskId,
      @RequestParam Map<String, MultipartFile> fileMap, @RequestParam Map<String, String> paramsMap,
      RedirectAttributes redirectAttributes) {
    //gX-tY-name;
    activitiesMapper.updateTaskTestCases(taskId, fileMap, paramsMap);
    System.out.println();
    return "";
  }

  @GetMapping("/tasks/{activityId}/cases")
  public String previewTaskForCasesEdit(Model model, @PathVariable Long activityId) {
    model.addAttribute("task", activitiesMapper.getTaskWithCasesPreview(activityId));
    model.addAttribute("bodyContent", "admin/task-cases");
    return "master";
  }

  @PostMapping("/lectures/{id}")
  public String editLecture(Model model, @PathVariable Long id,
      @ModelAttribute CreateLectureRequest request, RedirectAttributes redirectAttributes) {
    activitiesMapper.saveLecture(request, id);
    redirectAttributes.addAttribute("categoryId", request.getCategoryId());
    return "redirect:/activities";
  }

  @GetMapping("/lectures/add")
  public String addLectureMce(Model model) {
    model.addAttribute("activityTags", utilsMapper.getAllActivityTagsAsOptions());
    model.addAttribute("categories", utilsMapper.getAllCategoriesAsOptions());
    model.addAttribute("topics", utilsMapper.getAllTopicsAsOptions());
    model.addAttribute("lecture", new LectureEditDTO());
    model.addAttribute("bodyContent", "admin/edit-activity");
    return "master";
  }

  @GetMapping("/tasks/add")
  public String addTaskMce(Model model) {
    model.addAttribute("activityTags", utilsMapper.getAllActivityTagsAsOptions());
    model.addAttribute("categories", utilsMapper.getAllCategoriesAsOptions());
    model.addAttribute("topics", utilsMapper.getAllTopicsAsOptions());
    model.addAttribute("lecture", new LectureEditDTO());
    model.addAttribute("bodyContent", "admin/edit-task");
    return "master";
  }

  @GetMapping("/lectures/edit/{id}")
  public String editLectureMce(@PathVariable Long id, Model model) {
    LectureEditDTO lecture = activitiesMapper.getLectureEditDto(id);
    model.addAttribute("lecture", lecture);
    model.addAttribute("activityTags", utilsMapper.getAllActivityTagsAsOptions());
    model.addAttribute("categories", utilsMapper.getAllCategoriesAsOptions());
    model.addAttribute("topics", utilsMapper.getAllTopicsAsOptions());
    model.addAttribute("bodyContent", "admin/edit-activity");
    return "master";
  }

  @GetMapping("/lectures/{activityId}")
  public String previewLecture(Model model, @PathVariable Long activityId) {
    model.addAttribute("lecture", activitiesMapper.getLecturePreview(activityId));
    model.addAttribute("bodyContent", "admin/lecture-preview");
    return "master";
  }
}
