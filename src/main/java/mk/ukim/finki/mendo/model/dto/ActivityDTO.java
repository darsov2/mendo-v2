package mk.ukim.finki.mendo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.mendo.model.ActivityTag;
import mk.ukim.finki.mendo.model.Content;
import mk.ukim.finki.mendo.model.enums.ContentType;

import java.util.List;

@AllArgsConstructor
@Data
public class ActivityDTO {

  Long id;
  String name;
  String type;
  String source;
  List<String> tags;

  public static ActivityDTO toDTO(Content content) {
    return new ActivityDTO(content.getId(),
        content.getTitle(), content.getType().name(), content.getSource(),
        content.getTags().stream().map(ActivityTag::getName).toList());
  }
}
