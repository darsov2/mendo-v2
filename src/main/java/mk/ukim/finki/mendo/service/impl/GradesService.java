package mk.ukim.finki.mendo.service.impl;

import mk.ukim.finki.mendo.model.dto.OptionDTO;
import mk.ukim.finki.mendo.model.enums.Grade;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GradesService {
    public List<OptionDTO<String>> getGradesAsOptions() {
        return Arrays.stream(Grade.values()).map(grade -> new OptionDTO<>(grade.toString(), grade.getPrettyName())).toList();
    }
}
