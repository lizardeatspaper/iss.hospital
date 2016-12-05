package vut.fit.iss.service.user.stuff;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import vut.fit.iss.domain.dto.StaffDTO;
import vut.fit.iss.domain.user.staff.Nurse;

import java.util.Optional;

public interface NurseService {
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_STAFF')")
    Optional<Nurse> getById(Long id);

    @Transactional
    @PreAuthorize("hasRole('ROLE_STAFF')")
    Nurse persist(Nurse nurse);

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Nurse nurse);

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Nurse create(StaffDTO dto);
}
