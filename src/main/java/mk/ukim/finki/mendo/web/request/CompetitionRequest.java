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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Data
public class CompetitionRequest {
    private String title;
    private LocalDate startDate;
    private String startTime;
    private String endTime;
    private CompetitionTypes type;
    private String place;
    private String info;
    private LocalDateTime deadline;
    private Long cycleId;
    private Long parentId;
    private List<Long> roomIds;

    private List<Long> taskIds = new ArrayList<>();
    private List<Long> taskPoints = new ArrayList<>();

    public LocalDateTime getStartDateTime() {
        return LocalDateTime.of(
                startDate,
                LocalTime.parse(startTime)
        );
    }

    public LocalDateTime getEndDateTime() {
        return LocalDateTime.of(
                startDate,
                LocalTime.parse(endTime)
        );
    }
}