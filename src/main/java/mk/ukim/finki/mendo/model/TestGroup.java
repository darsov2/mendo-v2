package mk.ukim.finki.mendo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class TestGroup extends BaseEntity<Long> {
    private String name;
    private Integer points;
    @OneToMany(mappedBy = "testGroup")
    private List<TestCase> testCases;
    @ManyToOne
    private TestGroup dependantOn;
}
