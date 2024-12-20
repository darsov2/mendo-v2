package mk.ukim.finki.mendo.service.impl;

import mk.ukim.finki.mendo.model.School;
import mk.ukim.finki.mendo.model.dto.OptionDTO;
import mk.ukim.finki.mendo.model.enums.Grade;
import mk.ukim.finki.mendo.repository.SchoolRepository;
import mk.ukim.finki.mendo.service.SchoolService;
import mk.ukim.finki.mendo.web.request.SchoolRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;
    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public List<School> findAll() {
        return schoolRepository.findAll();
    }

    @Override
    public School findById(Long schoolId) {
        return schoolRepository.findById(schoolId).orElseThrow(RuntimeException::new);
    }

    @Override
    public School save(SchoolRequest request) {
        return schoolRepository.save(new School(request.getName(), request.getAddress()));
    }
}
