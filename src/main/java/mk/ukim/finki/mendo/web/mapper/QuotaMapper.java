package mk.ukim.finki.mendo.web.mapper;

import mk.ukim.finki.mendo.model.Competition;
import mk.ukim.finki.mendo.model.Quota;
import mk.ukim.finki.mendo.model.School;
import mk.ukim.finki.mendo.service.QuotaService;
import mk.ukim.finki.mendo.web.request.QuotasRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuotaMapper {

    private final QuotaService service;
    private final SchoolMapper schoolMapper;
    private final CompetitionMapper competitionMapper;

    public QuotaMapper(QuotaService service, SchoolMapper schoolMapper, CompetitionMapper competitionMapper) {
        this.service = service;
        this.schoolMapper = schoolMapper;
        this.competitionMapper = competitionMapper;
    }

    public List<Quota> bulkSave(Long competitionId, Map<String, String> request){
        Competition competition = competitionMapper.findById(competitionId);

        request.remove("competitionId");

        List<School> schools = schoolMapper.findAllById(request.keySet().stream().map(Long::parseLong).toList());

        List<Quota> quotas = schools.stream().map(s ->
                new Quota(Integer.parseInt(request.get(s.getId().toString())), s, competition)).toList();
        service.save(quotas);
        return quotas;
    }

    public List<Quota> editBulkSave(Long competitionId, Map<String, String> request){
        Competition competition = competitionMapper.findById(competitionId);
        request.remove("competitionId");

        List<School> schools = schoolMapper.findAllById(request.keySet().stream().map(Long::parseLong).toList());

        List<Quota> quotas = schools.stream().map(s ->
            new Quota(Integer.parseInt(request.get(s.getId().toString())), s, competition)).toList();

        return service.editQuotasForCompetition(competitionId, quotas);
    }

    public List<Quota> findQuotasForCompetition(Long competitionId){
        return service.findQuotasForCompetition(competitionId);
    }

}
