package vut.fit.iss.service.user.stuff;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import vut.fit.iss.domain.user.staff.Staff;

import java.util.Collection;
import java.util.Optional;

public interface StaffService {
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_STAFF')")
    Optional<Staff> getById(Long id);

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_STAFF')")
    Collection<Staff> getAll();

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_STAFF')")
    boolean isStaffExist(String username);

}
