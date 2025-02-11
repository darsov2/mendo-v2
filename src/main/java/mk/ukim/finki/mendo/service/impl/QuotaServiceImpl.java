package mk.ukim.finki.mendo.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.mendo.model.Quota;
import mk.ukim.finki.mendo.repository.QuotaRepository;
import mk.ukim.finki.mendo.service.QuotaService;
import mk.ukim.finki.mendo.web.mapper.CompetitionMapper;
import mk.ukim.finki.mendo.web.mapper.SchoolMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotaServiceImpl implements QuotaService {

    private final QuotaRepository repository;
    private final SchoolMapper schoolMapper;
    private final CompetitionMapper competitionMapper;

    public QuotaServiceImpl(QuotaRepository repository, SchoolMapper schoolMapper, CompetitionMapper competitionMapper) {
        this.repository = repository;
        this.schoolMapper = schoolMapper;
        this.competitionMapper = competitionMapper;
    }

    @Transactional
    @Override
    public List<Quota> save(List<Quota> quotas) {
        return repository.saveAll(quotas);
    }

    @Override
    public List<Quota> findQuotasForCompetition(Long competitionId) {
        return repository.findAllByCompetition_Id(competitionId);
    }

    @Transactional
    @Override
    public List<Quota> editQuotasForCompetition(Long competitionId, List<Quota> quotas) {
        repository.deleteAllByCompetition_Id(competitionId);
        return save(quotas);
    }
}
