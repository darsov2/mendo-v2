package mk.ukim.finki.mendo.model;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class BaseAuditedEntity<T> extends BaseEntity<T> {
    @CreatedDate
    protected LocalDateTime dateCreated;
    @LastModifiedDate
    protected LocalDateTime dateModified;
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    protected MendoUser createdBy;
    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    protected MendoUser lastModifiedBy;

}
