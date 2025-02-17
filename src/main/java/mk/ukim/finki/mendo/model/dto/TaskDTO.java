package mk.ukim.finki.mendo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.mendo.model.Task;
import mk.ukim.finki.mendo.model.Thread;

import java.util.List;

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
    Thread thread;
    List<TestGroupDTO> testGroups;

    public static TaskDTO toDTO(Task task) {
        return new TaskDTO(task.getId(), task.getTitle(), task.getText(), task.getInputFormat(),
                task.getOutputFormat(), task.getMemoryLimit(), task.getTimeLimit(), task.getThread(), task.getTestGroups().stream().map(TestGroupDTO::toDTO).toList());
    }
}
