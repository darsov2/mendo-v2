package mk.ukim.finki.mendo.model;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.model.enums.CompetitionTypes;
import mk.ukim.finki.mendo.web.controllers.BaseAuditedEntity;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import java.sql.SQLType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.annotations.Type;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import org.hibernate.type.SqlTypes;

@Entity
@NoArgsConstructor
@Data
public class Competition extends BaseAuditedEntity<Long> {

    String title;
    LocalDate startDate;
    LocalDateTime startTime;
    LocalDateTime endTime;
    @Enumerated(EnumType.STRING)
    CompetitionTypes type;
    String place;
    String info;
    LocalDateTime registrationOpens;
    LocalDateTime registrationCloses;
    LocalDateTime deadline;
    Boolean requiresRegistration;
    Boolean visibleToPublic;
    Boolean canStudentRegister;

    @ManyToOne
    CompetitionCycle cycle;

    @ManyToOne
    Competition parentCompetition;

    @ManyToMany
    List<MendoUser> moderators;




    public Competition(String title, LocalDate startDate, LocalDateTime startTime, LocalDateTime endTime, CompetitionTypes type, String place, String info, LocalDateTime deadline, CompetitionCycle cycle) {
        this.title = title;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.place = place;
        this.info = info;
        this.deadline = deadline;
        this.cycle = cycle;
    }

    public Competition(String title, LocalDate startDate, LocalDateTime startTime, LocalDateTime endTime, CompetitionTypes type, String place, String info, LocalDateTime deadline, CompetitionCycle cycle, Competition parent) {
        this.title = title;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.place = place;
        this.info = info;
        this.deadline = deadline;
        this.cycle = cycle;
        this.parentCompetition = parent;
    }

    public Competition(String title, LocalDate startDate, LocalDateTime startTime, LocalDateTime endTime, CompetitionTypes type, String place, String info, LocalDateTime deadline, Competition parent) {
        this.title = title;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.place = place;
        this.info = info;
        this.deadline = deadline;
        this.parentCompetition = parent;
    }

    public Competition(String title, LocalDate startDate, LocalDateTime startTime, LocalDateTime endTime, CompetitionTypes type, String place, String info, LocalDateTime deadline) {
        this.title = title;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.place = place;
        this.info = info;
        this.deadline = deadline;
    }


    public Long getId(){
        return id;
    }
}
