package mk.ukim.finki.mendo.service;

import java.util.List;
import mk.ukim.finki.mendo.model.Topic;

public interface TopicService {
  List<Topic> findAll();
  Topic findById(Long id);
  Topic create(Topic topic);
}
