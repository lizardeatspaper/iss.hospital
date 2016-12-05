package vut.fit.iss.service.user;

import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import vut.fit.iss.domain.dto.PatientDTO;
import vut.fit.iss.domain.user.Patient;

import java.util.Collection;
import java.util.Optional;

public interface PatientService {

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_STAFF') or ((authentication != null) and (id == authentication.id))")
    Optional<Patient> getById(@P("id") Long id);

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_STAFF')")
    Collection<Patient> getAll();

    @Transactional
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    Patient persist(Patient patient);

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_STAFF')")
    boolean isPatientExist(String username);

    @Transactional
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    void delete(Patient patient);

    @Transactional
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    Patient create(PatientDTO dto);
}
