package mk.ukim.finki.mendo.model.web.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class CompetitionCycleRequest {

    String name;
    LocalDate year;
    LocalDateTime registrationDeadline;

}

