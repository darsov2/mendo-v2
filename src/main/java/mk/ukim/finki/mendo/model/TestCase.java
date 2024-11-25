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
    String memoryLimit;
    Integer executionTimeLimit; //ms
    @Column(name = "test_case_group")
    String group;



}
