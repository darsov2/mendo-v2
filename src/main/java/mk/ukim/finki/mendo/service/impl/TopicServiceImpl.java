package mk.ukim.finki.mendo.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import mk.ukim.finki.mendo.model.Topic;
import mk.ukim.finki.mendo.repository.TopicRepository;
import mk.ukim.finki.mendo.service.TopicService;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {

  private final TopicRepository topicRepository;

  public TopicServiceImpl(TopicRepository topicRepository) {
    this.topicRepository = topicRepository;
  }

  @Override
  public List<Topic> findAll() {
    return topicRepository.findAll();
  }

  @Override
  public Topic findById(Long id) {
    return topicRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  @Override
  public Topic create(Topic topic) {
    return topicRepository.save(topic);
  }
}
