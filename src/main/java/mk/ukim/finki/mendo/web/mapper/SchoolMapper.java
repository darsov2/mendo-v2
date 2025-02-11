package mk.ukim.finki.mendo.web.mapper;


import mk.ukim.finki.mendo.model.School;
import mk.ukim.finki.mendo.service.SchoolService;
import mk.ukim.finki.mendo.web.request.SchoolRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolMapper {

    private final SchoolService schoolService;

    public SchoolMapper(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    public School saveSchool(SchoolRequest request){
        return schoolService.save(request);
    }

    public Object listSchools() {
        return schoolService.findAll();
    }
    public List<School> findAllById(List<Long> ids){
        return schoolService.findAllById(ids);
    }

    public School findById(Long id) {
        return schoolService.findById(id);
    }

    public School updateSchool(Long id, SchoolRequest request) {
        return schoolService.update(id, request);
    }
}

