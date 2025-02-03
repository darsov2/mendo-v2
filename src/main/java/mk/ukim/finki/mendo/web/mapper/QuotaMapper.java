package mk.ukim.finki.mendo.web.mapper;

import mk.ukim.finki.mendo.model.Quota;
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

        List<Quota> quotas = request.entrySet().stream().skip(1).map(set ->
            new Quota(Integer.parseInt(set.getValue()), schoolMapper.findById(Long.parseLong(set.getKey())), competitionMapper.findById(competitionId))).toList();
        service.save(quotas);
        return quotas;
    }

}
