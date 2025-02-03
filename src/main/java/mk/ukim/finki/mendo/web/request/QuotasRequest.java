package mk.ukim.finki.mendo.web.request;

import lombok.Data;
import mk.ukim.finki.mendo.model.Competition;
import mk.ukim.finki.mendo.model.School;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;

@Data
public class QuotasRequest {
//    Long competitionId;
    Map<String, String> schoolQuotas;
}
