package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Article extends BaseAuditedEntity<Long> {

    String title;
    @Column(columnDefinition = "text")
    String text;

    public Article(String title, String text) {
        this.title = title;
        this.text = text;
    }
}
