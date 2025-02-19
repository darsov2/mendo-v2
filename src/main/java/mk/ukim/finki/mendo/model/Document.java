package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.model.BaseAuditedEntity;

@Entity
@Table(name = "documents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document extends BaseAuditedEntity<Long> {

  private String fileName;
  private String contentType;
//  @Lob
  @Basic(fetch = FetchType.LAZY)
  @Column(columnDefinition = "BYTEA")
  private byte[] base64Data;
}
