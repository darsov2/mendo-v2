package mk.ukim.finki.mendo.web.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestGroupRequest {
  private Integer groupNumber;
  private Integer points;
//  private List<TestCaseDTO> testCases;
}

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class TestCaseDTO {
//  private Integer testNumber;
//  private Integer points;
//  private Integer timeLimit;
//  private Integer memoryLimit;
//  private MultipartFile inputFile;
//  private MultipartFile outputFile;
//}
