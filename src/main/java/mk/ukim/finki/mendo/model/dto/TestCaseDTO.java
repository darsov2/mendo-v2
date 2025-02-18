package mk.ukim.finki.mendo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.model.TestCase;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestCaseDTO {
  private Long id;
  private MultipartFile input;
  private MultipartFile output;
  private Integer memoryLimit;
  private Integer timeLimit;
  private Integer points;

  public static TestCaseDTO toDTO(TestCase testCase) {
    return new TestCaseDTO(testCase.getId(), null, null, testCase.getMemoryLimit(), testCase.getExecutionTimeLimit(), testCase.getPoints());
  }
}
