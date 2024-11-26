package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.web.controllers.BaseAuditedEntity;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Task extends BaseAuditedEntity<Long> {
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
    @ManyToMany
    List<ActivityTag> tags;
    @ManyToOne
    Category category;
}
