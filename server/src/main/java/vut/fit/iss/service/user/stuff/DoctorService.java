package vut.fit.iss.service.user.stuff;

import org.springframework.transaction.annotation.Transactional;
import vut.fit.iss.domain.user.staff.Doctor;

import java.util.Collection;
import java.util.Optional;

public interface DoctorService {
    @Transactional(readOnly = true)
    Optional<Doctor> getById(Long id);

    @Transactional(readOnly = true)
    Collection<Doctor> getAll();

    @Transactional
    Doctor persist(Doctor doctor);

}
