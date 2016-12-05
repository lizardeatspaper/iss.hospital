package vut.fit.iss.service.other;

import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import vut.fit.iss.domain.dto.MedicalHistoryDTO;
import vut.fit.iss.domain.other.MedicalHistory;

import java.util.Collection;
import java.util.Optional;

public interface MedicalHistoryService {

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_STAFF')")
    Optional<MedicalHistory> getById(Long id);

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_STAFF') or ((authentication != null) and (id == authentication.id))")
    Collection<MedicalHistory> getByPatientId(@P("id") Long id);

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    MedicalHistory create(MedicalHistoryDTO dto);

    @Transactional
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    MedicalHistory persist(MedicalHistory medicalHistory);

    @Transactional
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    void delete(MedicalHistory medicalHistory);

}
