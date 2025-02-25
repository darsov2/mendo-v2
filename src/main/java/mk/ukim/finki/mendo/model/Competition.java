package mk.ukim.finki.mendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.model.enums.CompetitionTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    Boolean hasSchedule = false;

    @ManyToOne
    Competition parentCompetition;

    @OneToMany(mappedBy = "competition", fetch = FetchType.LAZY)
    List<CompetitionTask> tasks;

    @ManyToMany
    @JoinTable(
            name = "competition_room",
            joinColumns = @JoinColumn(name = "competition_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id"))
    List<Rooms> rooms;

    @ManyToMany
    @JsonIgnoreProperties("competitions")
    List<MendoUser> moderators;
    public Competition(String title, LocalDate startDate, LocalDateTime startTime, LocalDateTime endTime, CompetitionTypes type, String place, String info, LocalDateTime deadline, CompetitionCycle cycle, List<Rooms> rooms,
                       Boolean requiresRegistration, Boolean visibleToPublic, Boolean canStudentRegister, LocalDateTime registrationOpens, LocalDateTime registrationCloses) {
        this.title = title;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.place = place;
        this.info = info;
        this.deadline = deadline;
        this.registrationCloses = deadline;
        this.cycle = cycle;
        this.rooms = rooms;
        this.requiresRegistration = requiresRegistration;
        this.visibleToPublic = visibleToPublic;
        this.canStudentRegister = canStudentRegister;
        this.registrationOpens = registrationOpens;
        this.registrationCloses = registrationCloses;
    }

    public Competition(String title, LocalDate startDate, LocalDateTime startTime, LocalDateTime endTime, CompetitionTypes type, String place, String info, LocalDateTime deadline, CompetitionCycle cycle, Competition parent, List<Rooms> rooms,
                       Boolean requiresRegistration, Boolean visibleToPublic, Boolean canStudentRegister, LocalDateTime registrationOpens, LocalDateTime registrationCloses) {
        this.title = title;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.place = place;
        this.info = info;
        this.deadline = deadline;
        this.registrationCloses = deadline;
        this.cycle = cycle;
        this.parentCompetition = parent;
        this.rooms = rooms;
        this.requiresRegistration = requiresRegistration;
        this.visibleToPublic = visibleToPublic;
        this.canStudentRegister = canStudentRegister;
        this.registrationOpens = registrationOpens;
        this.registrationCloses = registrationCloses;
    }

    public Competition(String title, LocalDate startDate, LocalDateTime startTime, LocalDateTime endTime, CompetitionTypes type, String place, String info, LocalDateTime deadline, Competition parent, List<Rooms> rooms,
                       Boolean requiresRegistration, Boolean visibleToPublic, Boolean canStudentRegister, LocalDateTime registrationOpens, LocalDateTime registrationCloses) {
        this.title = title;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.place = place;
        this.info = info;
        this.deadline = deadline;
        this.registrationCloses = deadline;
        this.parentCompetition = parent;
        this.rooms = rooms;

        this.requiresRegistration = requiresRegistration;
        this.visibleToPublic = visibleToPublic;
        this.canStudentRegister = canStudentRegister;
        this.registrationOpens = registrationOpens;
        this.registrationCloses = registrationCloses;
    }



    public Competition(String title, LocalDate startDate, LocalDateTime startTime, LocalDateTime endTime, CompetitionTypes type, String place, String info, LocalDateTime deadline, List<Rooms> rooms,
                       Boolean requiresRegistration, Boolean visibleToPublic, Boolean canStudentRegister, LocalDateTime registrationOpens, LocalDateTime registrationCloses) {
        this.title = title;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.place = place;
        this.info = info;
        this.deadline = deadline;
        this.registrationCloses = deadline;
        this.rooms = rooms;

        this.requiresRegistration = requiresRegistration;
        this.visibleToPublic = visibleToPublic;
        this.canStudentRegister = canStudentRegister;
        this.registrationOpens = registrationOpens;
        this.registrationCloses = registrationCloses;
    }


    public Long getId(){
        return id;
    }



}
