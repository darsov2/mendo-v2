package mk.ukim.finki.mendo.service;

import mk.ukim.finki.mendo.model.School;
import mk.ukim.finki.mendo.web.request.SchoolRequest;

import java.util.List;

public interface SchoolService {
    List<School> findAll();
    List<School> findAllById(List<Long> ids);
    School findById(Long schoolId);
    School save(SchoolRequest request);
}
