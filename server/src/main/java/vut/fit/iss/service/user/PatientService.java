package vut.fit.iss.service.user;

import org.springframework.transaction.annotation.Transactional;
import vut.fit.iss.domain.user.Patient;

import java.util.Collection;
import java.util.Optional;

public interface PatientService {

    @Transactional(readOnly = true)
    Optional<Patient> getById(Long id);

    @Transactional(readOnly = true)
    Collection<Patient> getAll();

    @Transactional
    Patient persist(Patient patient);

    @Transactional
    boolean isPatientExist(Patient patient);

    @Transactional
    void delete(Patient patient);
}
