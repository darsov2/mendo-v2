package mk.ukim.finki.mendo.web.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import mk.ukim.finki.mendo.model.CompetitionCycle;
import mk.ukim.finki.mendo.model.enums.CompetitionTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Data
public class CompetitionRequest {

    String title;
    LocalDate startDate;
    String startTime;
    String endTime;
    CompetitionTypes type;
    String place;
    String info;
    LocalDateTime deadline;
    Long cycleId;
    Long parentId;

    public LocalDateTime getStartDateTime() {
        return LocalDateTime.of(
                startDate,
                LocalTime.parse(startTime)
        );
    }

    public LocalDateTime getEndDateTime() {
        return LocalDateTime.of(
                startDate,
                LocalTime.parse(startTime)
        );
    }


}
