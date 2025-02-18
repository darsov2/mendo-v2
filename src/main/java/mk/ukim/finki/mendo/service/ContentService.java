package mk.ukim.finki.mendo.service;

import mk.ukim.finki.mendo.model.ActivityTag;
import mk.ukim.finki.mendo.model.Category;
import mk.ukim.finki.mendo.model.Content;

import java.util.List;
import mk.ukim.finki.mendo.model.Lecture;
import mk.ukim.finki.mendo.model.Task;
import mk.ukim.finki.mendo.model.TestGroup;
import mk.ukim.finki.mendo.model.Topic;

public interface ContentService {
    List<Content> findAll();
    List<Content> findAllByCategoryId(Long id);

    Lecture createLecture(Category category, List<ActivityTag> activityTags, String title,
        String source, String content, Topic topic);
    Lecture updateLecture(Long lectureId, Category category, List<ActivityTag> activityTags, String title,
        String source, String content, Topic topic);

    Task createTask(Category category, List<ActivityTag> activityTags, String title,
        String source, String content, String inputContent, String outputContent, Topic topic);
    Task updateTask(Long lectureId, Category category, List<ActivityTag> activityTags, String title,
        String source, String content, String inputContent, String outputContent, Topic topic);

    Lecture findLectureById(Long id);

    Task findTaskById(Long id);

    List<TestGroup> saveTestGroups(List<TestGroup> testGroups);
}
