package mk.ukim.finki.mendo.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import mk.ukim.finki.mendo.model.ActivityTag;
import mk.ukim.finki.mendo.repository.ActivityTagRepository;
import mk.ukim.finki.mendo.service.ActivityTagService;
import org.springframework.stereotype.Service;

@Service
public class ActivityTagServiceImpl implements ActivityTagService {

  private final ActivityTagRepository activityTagRepository;

  public ActivityTagServiceImpl(ActivityTagRepository activityTagRepository) {
    this.activityTagRepository = activityTagRepository;
  }

  @Override
  public List<ActivityTag> findAll() {
    return activityTagRepository.findAll();
  }

  @Override
  public ActivityTag findById(Long id) {
    return activityTagRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }
}
