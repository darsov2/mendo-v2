package mk.ukim.finki.mendo.web.controllers;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import mk.ukim.finki.mendo.model.BaseEntity;
import mk.ukim.finki.mendo.model.MendoUser;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseAuditedEntity<T> extends BaseEntity<T> {
    @CreatedDate
    protected LocalDateTime dateCreated;
    @LastModifiedDate
    protected LocalDateTime dateModified;
    @CreatedBy
    @ManyToOne
    protected MendoUser createdBy;
    @LastModifiedBy
    @ManyToOne
    protected MendoUser lastModifiedBy;

}
