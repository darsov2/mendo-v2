package mk.ukim.finki.mendo.model.dto;

import lombok.AllArgsConstructor;
import mk.ukim.finki.mendo.model.MendoUser;

import java.util.List;

@AllArgsConstructor
public class ResultDTO {
    public MendoUser mendoUser;
    public List<Integer> points;
    public Integer totalPoints = 0;
}
