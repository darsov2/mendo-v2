package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.model.enums.TestGroupFeedbackPolicy;
import mk.ukim.finki.mendo.model.enums.TestGroupPointsPolicy;

@Entity
@Data
public class TestGroup extends BaseEntity<Long> {
    private String name;
    private Integer points;
    @Column(name = "group_order")
    private Integer order;
    @OneToMany(mappedBy = "testGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestCase> testCases;
    @ManyToOne
    private TestGroup dependantOn;
    @Enumerated(EnumType.STRING)
    private TestGroupPointsPolicy pointsPolicy;
    @Enumerated(EnumType.STRING)
    private TestGroupFeedbackPolicy feedbackPolicy;
    @ManyToOne
    @JoinColumn(name = "task_content_id", referencedColumnName = "content_id")
    private Task task;
}
