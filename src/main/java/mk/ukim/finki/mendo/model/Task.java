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
    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    List<TestGroup> testGroups;

    @Column(columnDefinition = "text")
    String inputFormat;
    @Column(columnDefinition = "text")
    String outputFormat;
    Integer timeLimit; //in ms
    Integer memoryLimit; //in MB
}