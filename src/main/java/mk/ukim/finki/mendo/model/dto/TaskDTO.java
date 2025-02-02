package mk.ukim.finki.mendo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.mendo.model.Task;

@Data
@AllArgsConstructor
public class TaskDTO {

  Long id;
  String title;
  String text;
  String inputFormat;
  String outputFormat;
  Integer memoryLimit;
  Integer timeLimit;

  public static TaskDTO toDTO(Task task) {
    return new TaskDTO(task.getId(), task.getTitle(), task.getText(), task.getInputFormat(),
        task.getOutputFormat(), task.getMemoryLimit(), task.getTimeLimit());
  }
}
