package vut.fit.iss.service.user.stuff;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import vut.fit.iss.domain.dto.StaffDTO;
import vut.fit.iss.domain.user.staff.Administrator;

public interface AdministratorService {

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Administrator persist(Administrator administrator);

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Administrator create(StaffDTO dto);
}
