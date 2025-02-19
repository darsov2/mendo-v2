package mk.ukim.finki.mendo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.model.BaseAuditedEntity;
import org.hibernate.annotations.Fetch;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Topic extends BaseAuditedEntity<Long> {
  String title;
  @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY)
  List<Content> contents = new ArrayList<>();

  public Topic(String title) {
    this.title = title;
  }
}
