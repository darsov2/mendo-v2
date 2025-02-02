package mk.ukim.finki.mendo.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.model.BaseEntity;
import mk.ukim.finki.mendo.model.Lecture;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureEditDTO {

  Long id;
  String title;
  Long categoryId;
  Long topicId;
  List<Long> tagsId;
  String content;
  String source;

  public static LectureEditDTO toDTO(Lecture lecture) {
    return new LectureEditDTO(lecture.getId(), lecture.getTitle(), lecture.getCategory().getId(),
        lecture.getTopic() != null ? lecture.getTopic().getId() : null,
        lecture.getTags().stream().map(BaseEntity::getId).toList(),
        lecture.getText(), lecture.getSource());
  }
}
