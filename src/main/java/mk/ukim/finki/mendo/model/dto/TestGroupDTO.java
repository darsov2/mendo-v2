package mk.ukim.finki.mendo.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.model.TestGroup;
import mk.ukim.finki.mendo.model.enums.TestGroupFeedbackPolicy;
import mk.ukim.finki.mendo.model.enums.TestGroupPointsPolicy;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestGroupDTO {

    private Long id;
    private List<TestCaseDTO> testCases = new ArrayList<>();
    private Integer points;
    private TestGroupFeedbackPolicy feedbackPolicy;
    private TestGroupPointsPolicy pointsPolicy;

    public static TestGroupDTO toDTO(TestGroup testGroup) {
        return new TestGroupDTO(testGroup.getId(), testGroup.getTestCases().stream().map(x -> TestCaseDTO.toDTO(x)).toList(), testGroup.getPoints(), testGroup.getFeedbackPolicy(), testGroup.getPointsPolicy());
    }
}