package mk.ukim.finki.mendo.web.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.transaction.Transactional;
import mk.ukim.finki.mendo.model.*;
import mk.ukim.finki.mendo.model.dto.ActivityDTO;
import mk.ukim.finki.mendo.model.dto.LectureDTO;
import mk.ukim.finki.mendo.model.dto.LectureEditDTO;
import mk.ukim.finki.mendo.model.dto.TaskDTO;
import mk.ukim.finki.mendo.model.dto.TestCaseDTO;

import mk.ukim.finki.mendo.model.dto.TestGroupDTO;
import mk.ukim.finki.mendo.model.enums.TestGroupFeedbackPolicy;
import mk.ukim.finki.mendo.model.enums.TestGroupPointsPolicy;
import mk.ukim.finki.mendo.service.ActivityTagService;
import mk.ukim.finki.mendo.service.CategoryService;
import mk.ukim.finki.mendo.service.ContentService;
import mk.ukim.finki.mendo.service.TopicService;
import mk.ukim.finki.mendo.service.impl.DocumentService;
import mk.ukim.finki.mendo.web.request.CreateLectureRequest;
import mk.ukim.finki.mendo.web.request.CreateTaskRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ActivitiesMapper {

    private final ContentService contentService;
    private final CategoryService categoryService;
    private final ActivityTagService activityTagService;
    private final TopicService topicService;
    private final DocumentService documentService;

    public ActivitiesMapper(ContentService contentService, CategoryService categoryService,
                            ActivityTagService activityTagService, TopicService topicService, DocumentService documentService) {
        this.contentService = contentService;
        this.categoryService = categoryService;
        this.activityTagService = activityTagService;
        this.topicService = topicService;
        this.documentService = documentService;
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
        List<ActivityTag> activityTags = new ArrayList<>();
        if(request.getTagsId() != null) {
           activityTags = request.getTagsId().stream().map(activityTagService::findById)
                    .toList();
        }
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

    @Transactional
    public List<TestGroupDTO> updateTaskTestCases(Long taskId, Map<String, MultipartFile> fileMap, Map<String, String> paramsMap) {
        List<TestGroupDTO> groupsToUpdate = parseInputMaps(fileMap, paramsMap);

        Task task = contentService.findTaskById(taskId);

        List<TestGroup> groups = groupsToUpdate.stream().map(group -> {
            TestGroup testGroup = new TestGroup();
            testGroup.setOrder(Math.toIntExact(group.getId()));
            testGroup.setName("GROUP_" + group.getId());
            testGroup.setPoints(group.getPoints());
            testGroup.setFeedbackPolicy(group.getFeedbackPolicy());
            testGroup.setPointsPolicy(group.getPointsPolicy());
            testGroup.setTask(task);
            testGroup.setTestCases(group.getTestCases().stream().map(test -> {
                TestCase testCase = new TestCase();
                testCase.setExecutionTime(test.getTimeLimit());
                testCase.setTestGroup(testGroup);
                testCase.setInputFile(documentService.saveFile(test.getInput()));
                testCase.setOutputFile(documentService.saveFile(test.getOutput()));
                testCase.setMemoryLimit(test.getMemoryLimit());
                return testCase;
            }).toList());
            return testGroup;
        }).toList();

        groups = contentService.saveTestGroups(groups);


        return groupsToUpdate;
    }


    private List<TestGroupDTO> parseInputMaps(Map<String, MultipartFile> fileMap, Map<String, String> paramsMap) {
        Map<Long, TestGroupDTO> groups = new HashMap<>();

        Pattern testPattern = Pattern.compile("g(\\d+)-t(\\d+)-(\\w+)");
        Pattern groupPattern = Pattern.compile("g(\\d+)-(\\w+)");

        for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
            Matcher matcher = testPattern.matcher(entry.getKey());
            if (matcher.matches()) {
                Long groupId = Long.parseLong(matcher.group(1));
                Long testId = Long.parseLong(matcher.group(2));
                String paramName = matcher.group(3);

                TestGroupDTO group = groups.computeIfAbsent(groupId, k -> {
                    TestGroupDTO g = new TestGroupDTO();
                    g.setId(k);
                    return g;
                });

                TestCaseDTO test = group.getTestCases().stream()
                        .filter(t -> t.getId().equals(testId))
                        .findFirst()
                        .orElseGet(() -> {
                            TestCaseDTO t = new TestCaseDTO();
                            t.setId(testId);
                            group.getTestCases().add(t);
                            return t;
                        });

                switch (paramName) {
                    case "fileInput":
                        test.setInput(entry.getValue());
                        break;
                    case "fileOutput":
                        test.setOutput(entry.getValue());
                        break;
                }
            }
        }

        // Process parameters
        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            // First try to match group-level parameters
            Matcher groupMatcher = groupPattern.matcher(entry.getKey());
            if (groupMatcher.matches()) {
                Long groupId = Long.parseLong(groupMatcher.group(1));
                String paramName = groupMatcher.group(2);

                TestGroupDTO group = groups.computeIfAbsent(groupId, k -> {
                    TestGroupDTO g = new TestGroupDTO();
                    g.setId(k);
                    return g;
                });

                switch (paramName) {
                    case "feedback":
                        group.setFeedbackPolicy(TestGroupFeedbackPolicy.valueOf(entry.getValue()));
                        break;
                    case "points":
                        group.setPoints(Integer.parseInt(entry.getValue()));
                        break;
                    case "distribution":
                        group.setPointsPolicy(TestGroupPointsPolicy.valueOf(entry.getValue()));
                        break;
                }
                continue; // Skip test case parameter matching
            }

            // Then try to match test case parameters
            Matcher testMatcher = testPattern.matcher(entry.getKey());
            if (testMatcher.matches()) {
                Long groupId = Long.parseLong(testMatcher.group(1));
                Long testId = Long.parseLong(testMatcher.group(2));
                String paramName = testMatcher.group(3);

                TestGroupDTO group = groups.computeIfAbsent(groupId, k -> {
                    TestGroupDTO g = new TestGroupDTO();
                    g.setId(k);
                    return g;
                });

                TestCaseDTO test = group.getTestCases().stream()
                        .filter(t -> t.getId().equals(testId))
                        .findFirst()
                        .orElseGet(() -> {
                            TestCaseDTO t = new TestCaseDTO();
                            t.setId(testId);
                            group.getTestCases().add(t);
                            return t;
                        });

                switch (paramName) {
                    case "points":
                        test.setPoints(Integer.parseInt(entry.getValue()));
                        break;
                    case "memory":
                        test.setMemoryLimit(Integer.parseInt(entry.getValue()));
                        break;
                    case "time":
                        test.setTimeLimit(Integer.parseInt(entry.getValue()));
                        break;
                }
            }
        }

        return groups.values().stream().toList();
    }

    public LectureDTO getLecturePreview(Long lectureId) {
        Lecture lecture = contentService.findLectureById(lectureId);
        return LectureDTO.toDTO(contentService.findLectureById(lectureId));
    }

    public TaskDTO getTaskPreview(Long lectureId) {
        Task task = contentService.findTaskById(lectureId);
        return TaskDTO.toDTO(task);
    }

    public TaskDTO getTaskWithCasesPreview(Long lectureId) {
        Task task = contentService.findTaskById(lectureId);
        return TaskDTO.toDTO(task);
    }

    public mk.ukim.finki.mendo.model.Thread getThread(Long lectureId) {
        mk.ukim.finki.mendo.model.Thread thread = contentService.findTaskById(lectureId).getThread();
        return thread;
    }

    public LectureEditDTO getLectureEditDto(Long lectureId) {
        return LectureEditDTO.toDTO(contentService.findLectureById(lectureId));
    }
}
