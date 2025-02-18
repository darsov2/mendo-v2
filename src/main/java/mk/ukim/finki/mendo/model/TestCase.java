package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String input;
    String output;
    Integer executionTime; //ms
    Integer memoryLimit;
    Integer executionTimeLimit; //ms
    @ManyToOne(cascade = CascadeType.ALL)
    TestGroup testGroup;
    @ManyToOne(cascade = CascadeType.ALL)
    Document inputFile;
    @ManyToOne(cascade = CascadeType.ALL)
    Document outputFile;
    Boolean isExample;
    String furtherExplanation;
    @Column(name = "case_order")
    Integer order;
    Integer points;
}
