package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.web.controllers.BaseAuditedEntity;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "content_id")
public class Task extends Content {
    @OneToOne
    Thread thread;

    @ManyToMany
    @JoinTable(name = "category_task",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    List<Category> categories;

    @OneToMany
    List<TestCase> testCases;

    String inputFormat;

    String outputFormat;

    Boolean visible = true;
}