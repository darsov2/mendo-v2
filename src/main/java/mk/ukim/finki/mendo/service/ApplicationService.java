package mk.ukim.finki.mendo.service;

import mk.ukim.finki.mendo.model.Application;
import mk.ukim.finki.mendo.model.enums.Grade;

public interface ApplicationService {
    Application registerForCompetition(Long cycleId, Grade grade, Long schoolId);
    public boolean isUserAlreadyRegistered(Long cycleId);
}