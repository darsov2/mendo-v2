package mk.ukim.finki.mendo.model.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.mendo.model.Lecture;

@Data
@AllArgsConstructor
public class LectureDTO {

  String title;
  String text;
  TopicDto topic;

  public static LectureDTO toDTO(Lecture lecture) {
    return new LectureDTO(lecture.getTitle(), lecture.getText(),
        lecture.getTopic() != null ? new TopicDto(
            lecture.getTopic().getId(),
            lecture.getTopic().getTitle(),
            lecture.getTopic().getContents().stream()
                .map(x -> new LectureRecord(x.getId(), x.getTitle(), x.getId() == lecture.getId()))
                .toList()) : null
    );
  }
}

@Data
@AllArgsConstructor
class TopicDto {

  Long id;
  String title;
  List<LectureRecord> lectures = new ArrayList<>();
}

@Data
@AllArgsConstructor
class LectureRecord {

  Long id;
  String title;
  boolean active;
}
