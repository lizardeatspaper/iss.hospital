package vut.fit.iss.service.user.stuff;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import vut.fit.iss.domain.dto.StaffDTO;
import vut.fit.iss.domain.user.staff.Doctor;

import java.util.Optional;

public interface DoctorService {
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    Optional<Doctor> getById(Long id);

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Doctor persist(Doctor doctor);

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Doctor doctor);
    
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Doctor create(StaffDTO dto);

}
