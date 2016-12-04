package vut.fit.iss.service.other;

import org.springframework.transaction.annotation.Transactional;
import vut.fit.iss.domain.dto.MedicalHistoryDTO;
import vut.fit.iss.domain.other.MedicalHistory;

import java.util.Collection;
import java.util.Optional;

public interface MedicalHistoryService {
    @Transactional(readOnly = true)
    Optional<MedicalHistory> getById(Long id);

    @Transactional(readOnly = true)
    Collection<MedicalHistory> getByPatientId(Long id);

    @Transactional(readOnly = true)
    MedicalHistory create(MedicalHistoryDTO dto);

    @Transactional
    MedicalHistory persist(MedicalHistory medicalHistory);

    @Transactional
    void delete(MedicalHistory medicalHistory);

}
