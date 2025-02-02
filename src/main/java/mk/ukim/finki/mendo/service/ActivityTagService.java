package mk.ukim.finki.mendo.service;

import java.util.List;
import mk.ukim.finki.mendo.model.ActivityTag;

public interface ActivityTagService {
  List<ActivityTag> findAll();
  ActivityTag findById(Long id);
}
