package mk.ukim.finki.mendo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OptionDTO<T> {
    T id;
    String value;
}
