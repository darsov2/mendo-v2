package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne
    Thread thread;
    @ManyToMany
    @JoinTable(name="category_task",
    joinColumns = @JoinColumn(name = "task_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    List<Category> categories;
//    @Type(type = "jsonb")
//    @Column(name = "address", columnDefinition = "jsonb")
    @OneToMany
    List<TestCase> testCases;



}
