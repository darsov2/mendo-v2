package mk.ukim.finki.mendo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.web.controllers.BaseAuditedEntity;

@Entity
@Table(name = "documents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document extends BaseAuditedEntity<Long> {

  private String fileName;
  private String contentType;
  @Lob
  private String base64Data;
}
