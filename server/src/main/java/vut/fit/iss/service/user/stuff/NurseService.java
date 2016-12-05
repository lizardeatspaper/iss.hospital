package vut.fit.iss.service.user.stuff;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import vut.fit.iss.domain.dto.StaffDTO;
import vut.fit.iss.domain.user.staff.Nurse;

public interface NurseService {
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Nurse persist(Nurse nurse);

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Nurse nurse);

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Nurse create(StaffDTO dto);
}
