package mk.ukim.finki.mendo.service;

import mk.ukim.finki.mendo.model.Quota;

import java.util.List;

public interface QuotaService {

    public List<Quota> save(List<Quota> quotas);

    List<Quota> findQuotasForCompetition(Long competitionId);

    List<Quota> editQuotasForCompetition(Long competitionId, List<Quota> quotas);
}
