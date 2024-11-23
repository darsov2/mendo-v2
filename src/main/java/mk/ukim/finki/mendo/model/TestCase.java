package mk.ukim.finki.mendo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    String group;



}
