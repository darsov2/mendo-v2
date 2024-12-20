package mk.ukim.finki.mendo.web.mapper;


import mk.ukim.finki.mendo.model.School;
import mk.ukim.finki.mendo.service.SchoolService;
import mk.ukim.finki.mendo.web.request.SchoolRequest;
import org.springframework.stereotype.Service;

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
}

