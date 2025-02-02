package mk.ukim.finki.mendo.web.request;

import java.util.List;
import lombok.Data;

@Data
public class CreateTaskRequest {
  String title;
  List<Long> tagsId;
  String source;
  Long categoryId;
  String text;
  String inputText;
  String outputText;
  String manualTopicInput;
  Long topicId;
}
