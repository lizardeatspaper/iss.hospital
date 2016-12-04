package vut.fit.iss.service.other;

import vut.fit.iss.domain.other.MedicalHistory;

import java.util.Collection;
import java.util.Optional;

public interface MedicalHistoryService {
    Optional<MedicalHistory> getById(Long id);
    Collection<MedicalHistory> getByPatientId(Long id);

}
