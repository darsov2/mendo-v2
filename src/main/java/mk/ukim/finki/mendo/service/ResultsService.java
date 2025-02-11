package mk.ukim.finki.mendo.service;

import mk.ukim.finki.mendo.model.dto.ResultDTO;

import java.util.List;

public interface ResultsService {

    List<ResultDTO> getResultOfCompetition(Long competitionId);
}
